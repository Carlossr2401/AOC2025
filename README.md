# Advent of Code 2025 🎄

Este repositorio contiene las soluciones a los desafíos de programación del [Advent of Code 2025](https://adventofcode.com/2025), desarrolladas como parte de la asignatura de **Ingeniería de Software**.

El objetivo principal de este proyecto no es solo resolver los problemas algorítmicos, sino aplicar rigurosamente **buenas prácticas de diseño y arquitectura de software**. Se ha realizado un esfuerzo de refactorización masivo para estandarizar todos los días (del 02 al 12) bajo una arquitectura común.

## Arquitectura Estandarizada

Todas las soluciones siguen un patrón arquitectónico unificado que promueve la separación de responsabilidades y la inversión de dependencias.

### Diagrama Arquitectónico Común

```mermaid
classDiagram
    direction LR

    class Main {
        +main(args)
        <<Composition Root>>
    }

    class SolverFactory {
        +createSolver(...) Solver$
    }

    class ReaderFactory {
        +createFileReader(...) InstructionReader$
    }

    class Solver {
        <<interface>>
        +solve...() Object
    }

    class InstructionReader {
        <<interface>>
        +readInput() Object
    }

    class ConcreteSolver {
        -reader: InstructionReader
        -strategy: Strategy
        +solve...() Object
    }

    Main ..> SolverFactory : usa
    Main ..> ReaderFactory : usa
    Main ..> InstructionReader : inyecta

    SolverFactory ..> Solver : crea
    ReaderFactory ..> InstructionReader : crea

    ConcreteSolver ..|> Solver : implementa
    ConcreteSolver --> InstructionReader : recibe (Inyección de Dependencia)
```

### Componentes Clave

1.  **Main (Composition Root):**

    - Actúa como el punto de entrada y orquestador.
    - Su única responsabilidad es crear las dependencias (usando las Factories) e inyectarlas donde sea necesario.
    - No contiene lógica de negocio.

2.  **Factories (Static):**

    - `SolverFactory` y `ReaderFactory`.
    - Métodos estáticos puros para encapsular la complejidad de la creación de objetos.
    - Centralizan la selección de estrategias (e.g., Parte A vs Parte B).

3.  **Interfaces:**

    - `Solver`: Contrato común para resolver el problema (`solve()`, `solveProblem()`). Lanza `IOException` para manejo de errores centralizado.
    - `InstructionReader`: Contrato para la lectura de datos, desacoplando la fuente (Archivo, String, Mock) del consumidor.

4.  **Inyección de Dependencias (DIP):**
    - Los `Solvers` nunca instancian sus dependencias. Reciben `InstructionReader` o estrategias auxiliares a través de su constructor.
    - El control se invierte hacia el `Main`.

## Principios Aplicados

- **Principios SOLID:** Especial énfasis en **SRP** (Responsabilidad Única) y **DIP** (Inversión de Dependencias).
- **Clean Code:** Uso de nombres semánticos y métodos pequeños.
- **Patrones de Diseño:**
  - **Factory Pattern:** Para creación de objetos.
  - **Strategy Pattern:** Para intercambiar algoritmos (Parte A/B, algoritmos de búsqueda).
  - **Decorator Pattern:** (e.g., Día 11) Para añadir funcionalidad transversal como medición de tiempo.
  - **Iterator Pattern:** Para recorrer colecciones de modelos complejos.

## Estructura del Proyecto

El código fuente se organiza en paquetes por día, ubicados en `src/main/java/software/aoc/`.

```
src/main/java/software/aoc/
├── day02/  # ...
├── ...
├── day11/  # Ejemplo de uso de Decorator y Strategy
└── day12/  # Ejemplo de arquitectura compleja con Parsers y Estrategias de Colocación
```

## Tecnologías

- **Lenguaje:** Java 17+ (Uso extensivo de `records`, `switch expressions`, `var`).
- **Gestor de Dependencias:** Maven
- **Control de Versiones:** Git
