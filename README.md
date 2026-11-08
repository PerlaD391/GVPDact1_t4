# GVPDact1_t4 — CulturaVibe en Spring Boot

Act1. Introducción a Spring Boot — Configuración y Primeros Controladores.
Programación Web, equipo 13.

**Autora:** Perla Danae Gallardo Vasquez (GVPD)
**Proyecto:** CulturaVibe, calendario de eventos culturales en México.
**Stack:** Java 17, Spring Boot 3.5.16, Maven, Spring Web (Tomcat embebido).

La API replica en Spring Boot las partes principales de la app de React
(CulturaVibe): la cartelera de eventos, el panel principal (dashboard) y la
información del proyecto. Los datos son simulados, como pide la actividad
(en la app real los eventos vienen de Ticketmaster).

## Correr en local

Requisitos: JDK 17 y Maven.

```bash
mvn clean package
java -jar target/gvpdact1-t4-0.0.1-SNAPSHOT.jar
```

O en modo desarrollo:

```bash
mvn spring-boot:run
```

El servidor levanta en `http://localhost:8081`.

## Endpoints (3 controladores, 8 endpoints)

| Método | Ruta | Respuesta |
| ------ | ---- | --------- |
| GET | `/api/proyecto` | Nombre del proyecto e iniciales (GVPD) |
| GET | `/api/proyecto/integrantes` | Integrantes del equipo 13 |
| GET | `/api/proyecto/saludo?nombre=Perla` | Saludo en texto plano |
| GET | `/api/eventos` | Lista de eventos simulados |
| GET | `/api/eventos/{id}` | Un evento por id (ej. `ev-1`) |
| GET | `/api/eventos/estado/{estado}` | Eventos por estado (ej. `CDMX`) |
| GET | `/api/dashboard/resumen` | Resumen del panel principal |
| GET | `/api/dashboard/proximo-evento` | Próximo evento y días restantes |

Probar con el navegador o Postman, por ejemplo:

```bash
curl http://localhost:8081/api/proyecto
curl http://localhost:8081/api/dashboard/resumen
```

## Estructura

```
src/main/java/mx/culturavibe/act1/
├── CulturavibeAct1Application.java   # Arranque de Spring Boot
├── modelo/
│   ├── Evento.java                   # Evento de la cartelera
│   ├── Integrante.java               # Integrante del equipo
│   ├── ResumenDashboard.java         # Resumen del panel principal
│   └── ProximoEvento.java            # Próximo evento + días restantes
├── servicio/
│   ├── EventoService.java            # Catálogo simulado y búsquedas
│   └── DashboardService.java         # Datos del panel principal
└── controlador/
    ├── ProyectoController.java       # /api/proyecto
    ├── EventoController.java         # /api/eventos
    └── DashboardController.java      # /api/dashboard (ruta /dashboard de React)
src/main/resources/application.properties  # server.port=8081
```

## Despliegue en el VPS del equipo

VPS: `13.140.226.55` (Ubuntu 24.04, Contabo). Puerto asignado a GVPD: **8081**
(Ricardo, LSR, usa el **8082**).

```bash
# En el VPS (una sola vez)
sudo apt update && sudo apt install -y openjdk-17-jdk
java -version
sudo ufw allow 8081/tcp

# En tu máquina: compilar y subir el jar
mvn clean package
scp target/gvpdact1-t4-0.0.1-SNAPSHOT.jar root@13.140.226.55:/opt/springboot/

# En el VPS: correr en segundo plano
nohup java -jar /opt/springboot/gvpdact1-t4-0.0.1-SNAPSHOT.jar > /opt/springboot/app.log 2>&1 &

# Verificar desde fuera
curl http://13.140.226.55:8081/api/proyecto
```
