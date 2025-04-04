# Random-Fact-Gen
A RESTful service that fetches random facts from API-Ninjas, an external API , stores them in PostgreSQL, and serves them through a cache-enabled endpoint.

## Features

- 🚀 **Quarkus** powered backend
- 🐘 **PostgreSQL** database storage
- 🔄 **External API integration** (api-ninjas.com)
- ⚡ **Redis Caching** for improved performance
- 🐳 **Docker** container support
- ✅ **Test coverage** with Mockito and Testcontainers

## Prerequisites

- Java 17+
- Docker
- Maven 3.8+
- API key from [api-ninjas.com/facts](https://api-ninjas.com/api/facts)

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/isketton/random-fact-gen.git
   cd random-fact-gen

2. Run Application
   ```bash
   docker compose up -d
   ```

   ## How to use application

   ```bash
   curl -v http://localhost/facts


   
   
