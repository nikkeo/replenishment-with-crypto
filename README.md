# Описание функциональности проекта

Данный проект моделирует идею когда покупателю не хватает денег на счете карты при покупке и сервис автоматически переводит недостающую сумму с криптокошелька.
Проект представляет собой веб-приложение, которое позволяет пользователям управлять своими финансами, включая банковские карты и криптовалютные кошельки. Основные функции включают регистрацию, авторизацию, управление балансами, а также совершение операций с картами и криптовалютами.

## Основные функции

### Регистрация и авторизация пользователей
- Пользователи могут зарегистрироваться, указав имя пользователя и пароль.
- Реализована защита паролей с использованием хэширования (`generate_password_hash`).
- Сессии пользователей сохраняются для обеспечения персонализации.

### Панель управления (Dashboard)
- Отображает информацию о балансе карты и криптокошелька.
- Предоставляет доступ к функциям управления финансами.

### Привязка банковской карты
- Пользователи могут привязать карту, указав её номер, тип, срок действия и код CCV.
- Проверяется, чтобы у пользователя была возможность привязать только одну карту.

### Привязка криптовалютного кошелька
- Пользователи могут указать адрес криптовалютного кошелька.
- Реализована проверка, чтобы один пользователь мог привязать только один кошелёк.

### Пополнение баланса
- Пользователи могут пополнять баланс карты и криптокошелька через соответствующие формы.
- Баланс карты пополняется в долларах, а криптовалютный баланс — в выбранной валюте.

### Снятие средств
- Пользователь может снять средства с карты или криптокошелька.
- Если средств на карте недостаточно, недостающая сумма автоматически снимается с криптовалютного кошелька.
- Реализована проверка на достаточность средств на обоих балансах.

### Обновление курса валют
- Реализовано получение актуального курса криптовалют с использованием API.
- Автоматическое пересчитывание суммы в случае использования криптовалюты для оплаты.

### Уведомления
- Flash-сообщения уведомляют пользователей об успешных и ошибочных действиях (например, регистрация, авторизация, ошибки пополнения и т.д.).
