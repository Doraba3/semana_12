# Sistema de Triage de Urgencias

Este proyecto corresponde al **Taller de la Semana 12**, y tiene como objetivo implementar un sistema de triage para una sala de urgencias en un hospital.  
El programa permite registrar pacientes con distintos niveles de prioridad y simula el proceso de atención según el orden del triage.

---

## Funcionalidades principales

1. **Registrar pacientes** con su nombre y prioridad (1, 2 o 3).
2. **Ver el siguiente paciente** que será atendido.
3. **Atender pacientes** según el orden de prioridad (rojo, amarillo, verde).
4. **Mostrar contadores** de pacientes en espera por prioridad.

---

## Funcionalidades adicionales

Además de las funciones principales, el sistema también incluye algunas características opcionales:

- **Desempate por hora de llegada**, cuando dos pacientes tienen la misma prioridad.
- **Historial** que permite *deshacer la última atención* (reinsertar el paciente).
- **Reporte** simple con la lista de pacientes atendidos y en espera.

---

## Instrucciones de uso

1. Abre el proyecto en **IntelliJ IDEA**.
2. Crea un nuevo archivo llamado `TriageHospital.java` y pega el código del programa.
3. Ejecuta la aplicación desde el método `main`.
4. En la consola aparecerá un menú con las siguientes opciones:
    - **1. Registrar paciente**: Ingresa el nombre y la prioridad del paciente.
    - **2. Ver siguiente paciente**: Muestra el paciente que será atendido a continuación.
    - **3. Atender paciente**: Atiende al siguiente paciente según la prioridad.
    - **4. Mostrar contadores**: Muestra el número de pacientes en espera por cada nivel de prioridad.
    - **5. Deshacer última atención**: Reincorpora el último paciente atendido a la lista de espera.
    - **6. Salir**: Termina la ejecución del programa.

---

## Tecnologías utilizadas

- **Java 17**
- **IntelliJ IDEA**
- Estructuras de datos: `Queue`, `LinkedList`, `Stack` y `ArrayList`

---

## Autor

**Elaborado por:** Santiago Mosquera Parra  
- Semana 12 Taller  
