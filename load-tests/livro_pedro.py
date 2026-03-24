from locust import HttpUser, task, between
import json
import random

API_KEY = "sk-biblioteca-key-12345"

class LivroUserPedro(HttpUser):
    wait_time = between(1, 2)

    headers = {
        "X-API-Key": API_KEY,
        "Content-Type": "application/json"
    }

    @task(2)
    def criar_livro(self):
        payload = {
            "titulo": f"Livro Pedro {random.randint(1, 9999)}",
            "ano": random.randint(1900, 2024),
            "autor": {"id": 1}
        }
        with self.client.post(
            "/livros",
            headers=self.headers,
            data=json.dumps(payload),
            catch_response=True
        ) as response:
            if response.status_code == 201:
                response.success()
                livro = response.json()
                self.ultimo_id = livro.get("id", 1)
            else:
                response.failure(f"POST /livros falhou: {response.status_code}")

    @task(1)
    def ler_todos_livros(self):
        with self.client.get(
            "/livros",
            headers=self.headers,
            catch_response=True
        ) as response:
            if response.status_code == 200:
                response.success()
            else:
                response.failure(f"GET /livros falhou: {response.status_code}")