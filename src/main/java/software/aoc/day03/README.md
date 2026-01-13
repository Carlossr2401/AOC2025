# Advent of Code 2025 - Día 3: Sistema de Gestión de Voltajes

Este proyecto contiene la solución para el **Día 3** del Advent of Code 2025. El desafío consiste en procesar reportes de voltajes para identificar valores críticos o "máximos" dentro de series de datos y calcular la suma total de estos valores procesados.

## Diseño y Arquitectura

En este proyecto se aplican estrictamente los principios SOLID y Clean Code, junto con patrones de diseño estratégicos para garantizar un código mantenible, extensible y testeable.

### 1. Principios SOLID

- **Single Responsibility Principle (SRP)**:
  - `ReaderFactory` y `SolverFactory`: Responsables únicamente de la creación de objetos.
  - `FileInstructionReader`: Responsable de la lectura y parseo del archivo de entrada.
  - `SolverA` / `SolverB`: Coordinan la lógica de resolución para cada parte del problema.
  - `VoltageCalculator`: Encapsula la lógica de negocio para procesar listas de voltajes.
  - `MaxFinder`: Responsable específica de encontrar valores máximos (usado por el calculador).
- **Open/Closed Principle (OCP)**:
  - El sistema es extensible mediante interfaces. Se pueden agregar nuevos tipos de `InputReader` sin modificar los Solvers ni el orquestador principal.
  - Para agregar una nueva forma de procesar voltajes, basta con crear una nueva implementación de `VoltageProcessor`.
- **Liskov Substitution Principle (LSP)**:
  - `SolverA` y `SolverB` implementan la interfaz `Solver`, siendo intercambiables para el cliente (`Main`).
  - `FileInstructionReader` implementa `InputReader` y puede ser sustituido por cualquier otra fuente de datos (ej. red, base de datos) sin romper el sistema.
- **Interface Segregation Principle (ISP)**:
  - `InputReader`, `VoltageProcessor` y `Solver` son interfaces específicas que definen contratos claros asociados a una única funcionalidad.
- **Dependency Inversion Principle (DIP)**:
  - Los módulos de alto nivel (`Main`, `SolverA`, `SolverB`) dependen de abstracciones (`Solver`, `InputReader`, `VoltageProcessor`), no de implementaciones concretas.
  - La inyección de dependencias (`InputReader` en `SolverFactory`) se coordina desde el `Main`, invirtiendo el control de creación.

### 2. Patrones de Diseño

Se han implementado patrones de diseño para resolver problemas de creación y comportamiento:

- **Strategy Pattern (Estrategia)**:

  - La interfaz `Solver` define la estrategia de resolución del problema principal. `SolverA` y `SolverB` son estrategias concretas.
  - La interfaz `VoltageProcessor` define la estrategia para el cálculo de voltajes. `VoltageCalculator` es una implementación concreta que se inyecta en los solvers.
  - Internamente, `VoltageCalculator` utiliza `MaxFinder` como componente delegado para la lógica de búsqueda.

- **Factory Pattern (Fábrica)**:

  - `SolverFactory`: Centraliza la creación de los Solvers. Basado en un parámetro (`SolverType`), instancia la implementación adecuada (`SolverA` o `SolverB`) e inyecta las dependencias necesarias.
  - `ReaderFactory`: Abstrae la creación del lector de instrucciones (`InputReader`).

- **Dependency Injection**:
  - Las dependencias principales (`InputReader`) se inyectan en los constructores de `SolverA` y `SolverB` a través del `SolverFactory`.
  - `Main` orquesta la creación de dependencias y su inyección.

### 3. Diagrama de Arquitectura

```mermaid
classDiagram
    class Main {
        +main(args: String[]) void$
    }

    class SolverFactory {
        +createSolver(type: SolverType, reader: InputReader) Solver$
    }

    class ReaderFactory {
        +createReader(filePath: String) InputReader$
    }

    class Solver {
        <<Interface>>
        +solve() long
    }

    class SolverA {
        -reader: InputReader
        -calculator: VoltageProcessor
        +SolverA(reader: InputReader)
        +solve() long
    }

    class SolverB {
        -reader: InputReader
        -calculator: VoltageProcessor
        +SolverB(reader: InputReader)
        +solve() long
    }

    class InputReader {
        <<Interface>>
        +readAllVoltages() List~List~Integer~~
    }

    class VoltageProcessor {
        <<Interface>>
        +calculateHighestVoltage(voltages: List~Integer~) long
    }

    class FileInstructionReader {
        <<Record>>
        +readAllVoltages() List~List~Integer~~
    }

    class VoltageCalculator {
        +calculateHighestVoltage(voltages: List~Integer~) long
        -finder: MaxFinder
    }

    class MaxFinder {
        +findMax(values: List~Integer~) int
    }

    Main ..> SolverFactory : usa
    Main ..> ReaderFactory : usa
    Main ..> InputReader : usa (inyecta)
    SolverFactory ..> Solver : crea
    SolverFactory ..> SolverA : instancia
    SolverFactory ..> SolverB : instancia

    SolverA ..|> Solver : implementa
    SolverB ..|> Solver : implementa

    SolverA --> InputReader : usa
    SolverA --> VoltageProcessor : usa
    SolverB --> InputReader : usa
    SolverB --> VoltageProcessor : usa

    FileInstructionReader ..|> InputReader : implementa
    VoltageCalculator ..|> VoltageProcessor : implementa
    VoltageCalculator --> MaxFinder : usa
```

### 4. Estructura del Proyecto

La estructura de paquetes refleja la separación de responsabilidades:

- `software.aoc.day03`: Clases base, interfaces comunes y fábricas (`Solver`, `SolverFactory`, `InputReader`, `VoltageProcessor`, `FileInstructionReader`).
- `software.aoc.day03.a`: Implementación concreta para la Parte 1 (`SolverA`, `VoltageCalculator`, `MaxFinder`).
- `software.aoc.day03.b`: Implementación concreta para la Parte 2 (`SolverB`, `VoltageCalculator`, `MaxFinder`).
