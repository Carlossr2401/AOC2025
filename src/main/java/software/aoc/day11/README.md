# Advent of Code 2025 - Day 11

Este proyecto resuelve el desafío del Día 11 de Advent of Code 2025 implementando una arquitectura robusta basada en **SOLID**, **Clean Code** y **Patrones de Diseño** (Factory, Strategy, Decorator).

## Arquitectura del Proyecto

El código se ha refactorizado para soportar múltiples estrategias de resolución manteniendo una base común limpia y reutilizable, separando claramente la lógica de parsing, el grafo y el algoritmo de búsqueda.

### Diagrama de Clases Simplificado

```mermaid
classDiagram
    direction LR
    %% --- Paquete Principal: software.aoc.day11 ---
    namespace software_aoc_day11 {
        class Main {
            +main(args : String[])
        }

        class Solver {
            <<interface>>
            +solveProblem() Object
        }

        class InstructionReader {
            <<interface>>
            +readGraph() Graph
        }

        class FileInstructionReader {
            +readGraph() Graph
        }

        class ReaderFactory {
            +createFileReader(path : String) InstructionReader$
        }

        class SolverFactory {
            +createSolver(type : String, reader : InstructionReader, counter : PathCounter) Solver$
        }

        class Graph {
            <<record>>
            +adjacencyList() Map~String, List~String~~
            +getNeighbors(node : String) List~String~
        }

        class PathCounter {
            <<interface>>
            +countPaths(graph : Graph, start : String, end : String) long
        }

        class RecursivePathCounter {
            +countPaths(...) long
        }

        class TimedPathCounter {
            +countPaths(...) long
        }
    }

    %% --- Paquete Parte A: software.aoc.day11.a ---
    namespace software_aoc_day11_a {
        class Day11PartASolver {
            +solveProblem() Object
        }
    }

    %% --- Paquete Parte B: software.aoc.day11.b ---
    namespace software_aoc_day11_b {
        class Day11PartBSolver {
            +solveProblem() Object
        }
    }

    %% Relaciones Principales
    Main ..> SolverFactory : usa
    Main ..> ReaderFactory : usa
    Main ..> InstructionReader : usa (inyecta)
    Main ..> TimedPathCounter : instancia
    Main ..> RecursivePathCounter : instancia

    SolverFactory ..> Solver : crea instancias de
    ReaderFactory ..> FileInstructionReader : crea

    %% Implementaciones de Interfaces
    Day11PartASolver ..|> Solver : implementa
    Day11PartBSolver ..|> Solver : implementa
    FileInstructionReader ..|> InstructionReader : implementa
    RecursivePathCounter ..|> PathCounter : implementa
    TimedPathCounter ..|> PathCounter : implementa

    %% Relaciones de Uso (Dependencias)
    Day11PartASolver --> InstructionReader : inyectado
    Day11PartBSolver --> InstructionReader : inyectado
    Solver --> PathCounter : usa
    TimedPathCounter --> PathCounter : decora (delegación)
    InstructionReader ..> Graph : produce

### Estructura de Paquetes

- `software.aoc.day11`: **Núcleo Común**. Contiene las interfaces (`Solver`, `PathCounter`, `InstructionReader`), implementaciones genéricas (`RecursivePathCounter`, `TimedPathCounter`, `FileInstructionReader`), modelos (`Graph`), las Factorías (`SolverFactory`, `ReaderFactory`) y el punto de entrada (`Main`).
- `software.aoc.day11.a`: **Estrategia A**. Implementación específica para la Parte 1 (`Day11PartASolver`).
- `software.aoc.day11.b`: **Estrategia B**. Implementación específica para la Parte 2 (`Day11PartBSolver`).

## Patrones y Principios Aplicados

### 1. Strategy Pattern

- **Solver**: Permitimos seleccionar dinámicamente la estrategia de resolución (`Day11PartASolver` o `Day11PartBSolver`) a través del `SolverFactory`.
- **PathCounter**: La lógica de conteo de caminos se encapsula en una estrategia (`PathCounter`).

### 2. Factory Pattern

- **ReaderFactory**: Centraliza la creación del `InstructionReader`, devolviendo una implementación concreta.
- **SolverFactory**: Centraliza la creación de los objetos `Solver`. Recibe el `InstructionReader` y el `PathCounter` inyectados desde el `Main` y devuelve el resolvedor adecuado.

### 3. Decorator Pattern

- **TimedPathCounter**: Implementa el patrón Decorator sobre `PathCounter`. Envuelve una implementación concreta (como `RecursivePathCounter`) para añadir funcionalidad de medición de tiempo sin modificar la lógica original del algoritmo.

### 4. Dependency Injection (DIP)

Los `Solvers` no crean sus propias dependencias (lectores o contadores). Reciben el `InstructionReader` y el `PathCounter` a través de su constructor, orquestados por el `Main`. Esto reduce el acoplamiento y facilita el testeo.

### 5. Single Responsibility Principle (SRP)

- **InstructionReader**: Define el contrato para leer el grafo. `FileInstructionReader` implementa la lectura de archivo.
- **PathCounter**: Se encarga únicamente del algoritmo de búsqueda de caminos.
- **Solver**: Orquesta la resolución, pidiendo al lector el grafo y usando el contador para obtener resultados.
- **Main**: Se encarga de la configuración inicial, creación de dependencias y orquestación.
```
