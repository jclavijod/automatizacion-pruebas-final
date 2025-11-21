# Proyecto de Automatización de Pruebas - Examen Final

Este repositorio contiene la implementación de un proyecto de automatización de pruebas, siguiendo los requisitos de un examen final. Incluye un proyecto Maven con pruebas unitarias y de aceptación, configurado con pipelines de Integración Continua (CI) y Despliegue Continuo (CD) utilizando GitHub Actions.

---

## 🚀 Actividad 1: Repositorio y Proyecto Maven

### 1.1. Estructura del Repositorio

El repositorio está configurado con un flujo de trabajo de ramas que combina elementos de **Trunk-Based Development** y **GitFlow simplificado**.

-   **`main`**: Rama principal que representa el código listo para producción. Los despliegues se realizan desde esta rama.
-   **`develop`**: Rama de desarrollo principal donde se integran las nuevas funcionalidades y correcciones. Los Pull Requests se dirigen a esta rama antes de ser fusionados a `main`.

### 1.2. Proyecto Maven

El proyecto principal se encuentra en la carpeta `demo-app/`. Es una aplicación Java gestionada con Maven, que incluye las siguientes dependencias para pruebas:

-   **JUnit 5**: Framework para la escritura y ejecución de pruebas unitarias y de integración.
-   **Selenium Java**: Biblioteca para la automatización de navegadores web, utilizada para pruebas de interfaz de usuario (UI) o pruebas de integración más complejas.

#### Cómo ejecutar las pruebas localmente:

1.  Asegúrate de tener Java JDK 17 y Maven instalados.
2.  Navega a la carpeta `demo-app/`:

    cd demo-app

3.  Ejecuta las pruebas unitarias:

    mvn test

4.  Ejecuta las pruebas de aceptación (requiere el perfil `acceptance-tests`):

    mvn verify -Pacceptance-tests


---

## ⚙️ Actividad 2: Pipeline de Integración Continua (CI)

Se ha implementado un pipeline de CI utilizando **GitHub Actions** para automatizar la compilación y ejecución de pruebas unitarias en cada cambio de código.

### 2.1. Configuración del Pipeline

El workflow de CI se define en `.github/workflows/ci-maven.yml`.

-   **Disparadores**: Se ejecuta automáticamente en cada `push` y `pull_request` a las ramas `main` y `develop`.
-   **Pasos**:
    1.  **Checkout code**: Descarga el código del repositorio.
    2.  **Set up JDK 17**: Configura el entorno Java 17.
    3.  **Build and test with Maven**: Compila el proyecto y ejecuta todas las pruebas unitarias (`mvn test`).
    4.  **Upload test reports**: Sube los reportes de las pruebas (generados por Surefire) como artefactos descargables.

### 2.2. Acceso a los Resultados

Los resultados de cada ejecución del pipeline de CI pueden ser consultados en la pestaña [Actions](https://github.com/jclavijod/automatizacion-pruebas-final/actions) del repositorio. Los reportes de pruebas pueden descargarse desde la sección "Artifacts" de cada ejecución exitosa.

---

## 🚀 Actividad 3: Pipeline de Despliegue, Aceptación y Rollback

Se ha configurado un segundo pipeline en GitHub Actions para simular un proceso de despliegue continuo, incluyendo pruebas de aceptación y un mecanismo de rollback lógico.

### 3.1. Configuración del Pipeline de Despliegue

El workflow de CD se define en `.github/workflows/cd-maven.yml`.

-   **Disparadores**: Se ejecuta automáticamente en cada `push` a la rama `main`.
-   **Pasos**:
    1.  **Checkout code**: Descarga el código del repositorio.
    2.  **Set up JDK 17**: Configura el entorno Java 17.
    3.  **Run unit tests**: Ejecuta las pruebas unitarias para asegurar la calidad del código antes del empaquetado.
    4.  **Package application (build JAR)**: Empaqueta la aplicación Java en un archivo `.jar` ejecutable.
    5.  **Run acceptance tests**: Ejecuta las pruebas de aceptación definidas en el perfil Maven `acceptance-tests`. Estas pruebas validan el comportamiento de alto nivel de la aplicación.
    6.  **Upload JAR artifact**: Si todas las pruebas anteriores son exitosas, el archivo `.jar` generado se sube como un artefacto, simulando un despliegue exitoso de la nueva versión.

### 3.2. Mecanismo de Rollback (Lógico)

El pipeline de CD implementa un mecanismo de rollback lógico:

-   Si alguna de las pruebas (unitarias o de aceptación) falla, el pipeline se detiene y se marca como fallido.
-   En este escenario, el paso de `Upload JAR artifact` no se ejecuta, lo que significa que la nueva versión no se "despliega" o no se considera apta para producción. Esto asegura que solo las versiones estables y validadas lleguen a un estado de "despliegue", manteniendo la última versión funcional activa.

---

## 🤝 Contribución

Para contribuir a este proyecto, por favor, sigue los siguientes pasos:

1.  Clona el repositorio.
2.  Crea una nueva rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3.  Realiza tus cambios y asegúrate de que todas las pruebas pasen localmente.
4.  Haz `commit` de tus cambios (`git commit -m "feat: agrega nueva funcionalidad"`).
5.  Sube tu rama (`git push origin feature/nueva-funcionalidad`).
6.  Abre un Pull Request a la rama `develop`.

---