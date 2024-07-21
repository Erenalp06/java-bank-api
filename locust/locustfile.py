import random
from locust import HttpUser, task, between
from sqlalchemy import create_engine, MetaData, Table
from sqlalchemy.orm import sessionmaker

DATABASE_URL = "postgresql://postgres:postgres@localhost:5455/postgres"

engine = create_engine(DATABASE_URL)
metadata = MetaData()
accounts_table = Table('accounts', metadata, autoload_with=engine)

Session = sessionmaker(bind=engine)
session = Session()

account_numbers = []
with session.connection() as conn:
    result = conn.execute(accounts_table.select())
    for row in result.mappings():
        account_numbers.append(row['account_number'])

class BankApiUser(HttpUser):
    wait_time = between(1, 5)

    @task
    def transfer(self):
        source_account = random.choice(account_numbers)
        destination_account = random.choice([acc for acc in account_numbers if acc != source_account])
        amount = random.uniform(100, 150)

        transaction_request = {
            "sourceAccountNumber": source_account,
            "destinationAccountNumber": destination_account,
            "amount": amount
        }

        self.client.post("/api/v1/transactions/transfer", json=transaction_request)

    @task
    def deposit(self):
        account = random.choice(account_numbers)
        amount = random.uniform(50, 100)

        transaction_request = {
            "destinationAccountNumber": account,
            "amount": amount
        }

        self.client.post("/api/v1/transactions/deposit", json=transaction_request)

    @task
    def withdraw(self):
        account = random.choice(account_numbers)
        amount = random.uniform(50, 100)

        transaction_request = {
            "sourceAccountNumber": account,
            "amount": amount
        }

        self.client.post("/api/v1/transactions/withdraw", json=transaction_request)

    @task
    def payment(self):
        account = random.choice(account_numbers)
        amount = random.uniform(20, 80)
        payee = "Test Payee"

        transaction_request = {
            "sourceAccountNumber": account,
            "amount": amount,
            "payee": payee
        }

        self.client.post("/api/v1/transactions/payment", json=transaction_request)

    @task
    def refund(self):
        account = random.choice(account_numbers)
        amount = random.uniform(20, 80)

        transaction_request = {
            "destinationAccountNumber": account,
            "amount": amount
        }

        self.client.post("/api/v1/transactions/refund", json=transaction_request)

    @task
    def fee(self):
        account = random.choice(account_numbers)
        amount = random.uniform(1, 10)

        transaction_request = {
            "sourceAccountNumber": account,
            "amount": amount
        }

        self.client.post("/api/v1/transactions/fee", json=transaction_request)

    @task
    def interest(self):
        account = random.choice(account_numbers)
        amount = random.uniform(1, 10)

        transaction_request = {
            "destinationAccountNumber": account,
            "amount": amount
        }

        self.client.post("/api/v1/transactions/interest", json=transaction_request)
