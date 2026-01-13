# Advent of Code 2025 - Day 12

Este proyecto resuelve el desafío del Día 12 de Advent of Code 2025 implementando una arquitectura modular basada en **SOLID**, **Clean Code** y **Patrones de Diseño**.

## Arquitectura del Proyecto

El sistema divide claramente las responsabilidades entre la orquestación (App), la lógica de negocio (Service) y los datos (Model). Se ha puesto énfasis en la **Inversión de Dependencias** y la **Responsabilidad Única**.

### Diagrama de Clases

```mermaid
classDiagram
    direction LR

    %% Capa de Aplicación
    namespace app {
        class Main {
            +main(String[] args)
        }
    }

    %% Capa de Dominio/Servicio
    namespace software_aoc_day12 {
        class Solver {
            <<interface>>
            +solveProblem() Object
        }

        class InstructionReader {
            <<interface>>
            +readInput() List~String~
        }

        class FileInstructionReader {
            +readInput() List~String~
        }

        class ReaderFactory {
            +createFileReader(path) InstructionReader$
        }

        class SolverFactory {
            +createSolver(type, reader, parser, strategy) Solver$
        }
    }

    %% Capa de Servicio
    namespace service {
        class Day12Solver {
            +solveProblem() Object
        }

        class InputParser {
            <<interface>>
            +parseShapes(List~String~) List~Shape~
            +parseProblems(List~String~) List~RegionProblem~
        }

        class Day12InputParser {
            +parseShapes(List~String~) List~Shape~
            +parseProblems(List~String~) List~RegionProblem~
        }

        class ShapeFactory {
            +createShape(int id, List~String~ lines) Shape
        }

        class PlacementStrategy {
            <<interface>>
            +solve(Grid grid, List~Shape~ shapes) boolean
        }

        class BacktrackingSolver {
            +solve(Grid grid, List~Shape~ shapes) boolean
        }
    }

    %% Capa de Modelo
    namespace model {
        class Shape {
            +rotate() Shape
            +flip() Shape
            +get(r, c) boolean
        }
        class Grid {
            +canPlace(Shape, x, y) boolean
            +place(Shape, x, y, id)
        }
        class RegionProblem {
            +width() int
            +length() int
            +requiredShapeCounts() Map
        }
    }

    %% Relaciones
    Main --> SolverFactory : usa
    Main --> ReaderFactory : usa
    Main --> InstructionReader : usa (inyecta)
    Main --> PlacementStrategy : instancia
    Main --> InputParser : instancia

    SolverFactory --> Solver : crea (Day12Solver)
    ReaderFactory --> FileInstructionReader : crea

    Day12Solver ..|> Solver : implementa
    FileInstructionReader ..|> InstructionReader : implementa

    Day12Solver --> InstructionReader : inyectado
    Day12Solver --> InputParser : inyectado (DIP)
    Day12Solver --> PlacementStrategy : inyectado (DIP)

    Day12InputParser ..|> InputParser : Implementa
    BacktrackingSolver ..|> PlacementStrategy : Implementa
    Day12InputParser --> ShapeFactory : Usa para crear objetos

    BacktrackingSolver --> Grid : Manipula
    BacktrackingSolver --> Shape : Manipula
```

## Estructura de Paquetes

- `software.aoc.day12`: **Núcleo Común**. Contiene las interfaces (`Solver`, `InstructionReader`), sus implementaciones/factorías (`FileInstructionReader`, `SolverFactory`, `ReaderFactory`).
- `software.aoc.day12.app`: Contiene el **Punto de Entrada**. `Main` actúa como **Composition Root**, orquestando la creación de dependencias.
- `software.aoc.day12.service`: Contiene la lógica de negocio, interfaces de servicio, el resolvedor principal `Day12Solver` (que implementa `Solver`), factorías y estrategias.
- `software.aoc.day12.model`: Contiene los objetos de dominio puros (`Shape`, `Grid`, `RegionProblem`).

## Patrones y Principios Aplicados

### 1. Composition Root y Dependency Injection

`Main` orquesta toda la creación de dependencias, inyectando `InstructionReader`, `InputParser` y `PlacementStrategy` en el `Solver` a través de la `SolverFactory`.

### 2. Strategy Pattern

Se define `PlacementStrategy` para intercambiar algoritmos de resolución (e.g., Backtracking vs DLX). `SolverFactory` recibe la estrategia a utilizar.

### 3. Factory Pattern

- **ReaderFactory**: Centraliza la creación del lector.
- **SolverFactory**: Centraliza la creación del Solver.
- **ShapeFactory**: Encapsula el parseo de formas.

### 4. Single Responsibility Principle (SRP)

- **InstructionReader**: Solo IO.
- **InputParser**: Solo estructura datos.
- **Day12Solver**: Solo coordina (lee, parsea, delega resolución).
- **BacktrackingSolver**: Solo resuelve.

### 5. Dependency Inversion Principle (DIP)

`Day12Solver` depende de abstracciones (`InstructionReader`, `InputParser`, `PlacementStrategy`) inyectadas desde fuera, no de concreciones.
