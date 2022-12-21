# ReadingIsGood

- This project has a monolithic architecture and hexagonal design.

## Subdomains

- User
- Product
- Order
- Statistic


## Tech Stack

- Java
- Spring
- Docker
- Docker Compose

## Docker file was designed for multiple staging that allows us to use docker cache system and build images faster.
## Docker compose file has a mysql db. You dont need extra database.

How to run:
-------------------
- You change .env file by your system but it is not neccessary.
- "docker compose up -d --build" run this command

## Admin User
- You can define your admin user by changing .env file.

## Postman Collection
- You can import postman.json
- Every request has an example
- You need to provide token so you need to authenticate with admin or customer before