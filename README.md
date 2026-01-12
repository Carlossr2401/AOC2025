# Advent of Code 2025 🎄

Este repositorio contiene las soluciones a los desafíos de programación del [Advent of Code 2025](https://adventofcode.com/2025), desarrolladas como parte de la asignatura de **Ingeniería de Software**.

El objetivo principal de este proyecto no es solo resolver los problemas algorítmicos, sino aplicar rigurosamente **buenas prácticas de diseño y arquitectura de software**.

## Enfoque del Proyecto

Cada solución ha sido diseñada priorizando la calidad del código, la mantenibilidad y la testabilidad. Se han aplicado los siguientes conceptos clave:

- **Principios SOLID:** Estructura robusta y desacoplada.
- **Clean Code:** Código legible y auto-explicativo.
- **Patrones de Diseño:** Uso intensivo de patrones para resolver problemas comunes de diseño (e.g., _Strategy_, _Factory_, _Dependency Injection_).
- **Testing:** Enfoque orientado a pruebas (TDD) para garantizar la corrección de las soluciones.

## Estructura del Proyecto

El código fuente se organiza en paquetes por día, ubicados en `src/main/java/software/aoc/`.

```
src/main/java/software/aoc/
├── day01/  # Solución Día 1
├── day02/  # Solución Día 2
├── ...
└── day12/  # Solución Día 12 (y siguientes)
```

Cada paquete diario suele seguir una estructura arquitectónica consistente, separando claramente:

- **Solvers:** Lógica de resolución del problema.
- **Readers:** Lectura y procesamiento de entradas.
- **Factories:** Creación de instancias complejas.
- **Models:** Clases de dominio específicas del problema.

## Tecnologías

- **Lenguaje:** Java
- **Gestor de Dependencias:** Maven
- **Control de Versiones:** Git
