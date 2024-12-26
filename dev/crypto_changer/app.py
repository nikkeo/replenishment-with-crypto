from flask import Flask, render_template, request, redirect, url_for, session, flash
from werkzeug.security import generate_password_hash, check_password_hash
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
import requests

def get_crypto_to_fiat_rate(crypto_currency='bitcoin', fiat_currency='usd'):
    url = f'https://api.coingecko.com/api/v3/simple/price?ids={crypto_currency}&vs_currencies={fiat_currency}'
    response = requests.get(url)
    data = response.json()
    return data[crypto_currency][fiat_currency]

app = Flask(__name__)
app.secret_key = "supersecretkey"
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///users.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(150), nullable=False, unique=True)
    password = db.Column(db.String(200), nullable=False)
    card_balance = db.Column(db.Float, default=0.0)
    crypto_balance = db.Column(db.Float, default=0.0)
    card_number = db.Column(db.String(16), nullable=True, unique=True)
    card_type = db.Column(db.String(50), nullable=True)
    card_expiry = db.Column(db.String(5), nullable=True)
    card_ccv = db.Column(db.String(3), nullable=True)
    wallet_address = db.Column(db.String(200), nullable=True)

migrate = Migrate(app, db)

@app.route('/')
def home():
    if 'user_id' in session:
        return redirect(url_for('dashboard'))
    return render_template('home.html')


@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        hashed_password = generate_password_hash(password, method='pbkdf2:sha256')

        existing_user = User.query.filter_by(username=username).first()
        if existing_user:
            flash('Username already exists!', 'error')
            return redirect(url_for('register'))

        new_user = User(username=username, password=hashed_password, card_balance=0, crypto_balance=0)
        db.session.add(new_user)
        db.session.commit()

        flash('Registration successful! Please log in.', 'success')
        return redirect(url_for('login'))

    return render_template('register.html')


@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

        user = User.query.filter_by(username=username).first()
        if user and check_password_hash(user.password, password):
            session['user_id'] = user.id
            flash('Login successful!', 'success')
            return redirect(url_for('dashboard'))
        else:
            flash('Invalid username or password!', 'error')
            return redirect(url_for('login'))

    return render_template('login.html')


@app.route('/dashboard')
def dashboard():
    if 'user_id' not in session:
        return redirect(url_for('login'))

    user = User.query.get(session['user_id'])
    return render_template('dashboard.html', user=user)

@app.route('/logout')
def logout():
    session.pop('user_id', None)
    flash('Logged out successfully', 'success')
    return redirect(url_for('login'))

# Templates (for simplicity)
@app.route('/templates/<template_name>.html')
def serve_template(template_name):
    return render_template(f'{template_name}.html')

@app.route('/link_card', methods=['POST'])
def link_card():
    if 'user_id' not in session:
        return redirect(url_for('login'))

    user = User.query.get(session['user_id'])

    # Проверка, чтобы добавить только одну карту
    if user.card_number:
        flash('You already have a card linked!', 'error')
        return redirect(url_for('dashboard'))

    card_number = request.form['card_number']
    card_type = request.form['card_type']
    card_expiry = request.form['card_expiry']
    card_ccv = request.form['card_ccv']

    user.card_number = card_number
    user.card_type = card_type
    user.card_expiry = card_expiry
    user.card_ccv = card_ccv

    db.session.commit()

    flash('Card linked successfully!', 'success')
    return redirect(url_for('dashboard'))



@app.route('/link_wallet', methods=['POST'])
def link_wallet():
    if 'user_id' not in session:
        return redirect(url_for('login'))

    user = User.query.get(session['user_id'])

    if user.wallet_address:
        flash('You already have a wallet linked!', 'error')
        return redirect(url_for('dashboard'))

    wallet_address = request.form['wallet_address']

    user.wallet_address = wallet_address

    db.session.commit()

    flash('Wallet linked successfully!', 'success')
    return redirect(url_for('dashboard'))



@app.route('/deposit_card', methods=['POST'])
def deposit_card():
    if 'user_id' not in session:
        return redirect(url_for('login'))

    user = User.query.get(session['user_id'])
    amount = float(request.form['amount'])

    if user.card_number:
        user.card_balance += amount
        db.session.commit()
        flash('Card balance updated successfully!', 'success')
    else:
        flash('No card linked. Please link a card first.', 'error')

    return redirect(url_for('dashboard'))



@app.route('/deposit_wallet', methods=['POST'])
def deposit_wallet():
    if 'user_id' not in session:
        return redirect(url_for('login'))

    user = User.query.get(session['user_id'])
    amount = float(request.form['amount'])

    if user.wallet_address:
        user.crypto_balance += amount
        db.session.commit()
        flash('Wallet balance updated successfully!', 'success')
    else:
        flash('No wallet linked. Please link a wallet first.', 'error')

    return redirect(url_for('dashboard'))


@app.route('/withdraw', methods=['POST'])
def withdraw():
    if 'user_id' not in session:
        return redirect(url_for('login'))

    user = User.query.get(session['user_id'])
    amount_usd = float(request.form['amount_usd'])
    try:
        crypto_to_fiat_rate = get_crypto_to_fiat_rate('bitcoin', 'usd')
    except Exception as e:
        flash("Failed to fetch crypto to fiat rate.", 'danger')
        return redirect(url_for('dashboard'))


    if user.card_balance >= amount_usd:
        user.card_balance -= amount_usd
    else:
        needed_from_crypto = amount_usd - user.card_balance
        crypto_needed = needed_from_crypto / crypto_to_fiat_rate

        if user.crypto_balance >= crypto_needed:
            user.crypto_balance -= crypto_needed
            user.card_balance = 0
        else:
            flash('Insufficient funds on card and crypto wallet', 'danger')
            return redirect(url_for('dashboard'))


    db.session.commit()
    flash('Withdrawal successful!', 'success')
    return redirect(url_for('dashboard'))


if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True)

