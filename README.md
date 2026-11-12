# UPS Java Training

A team-maintained repository for Java training exercises, assignments, and practical projects.

## Repository structure

| Folder | Purpose |
|---|---|
| `DAY 1` – `DAY 10` | Daily Java training exercises |
| `DAY 10 (Supermarket)` | Supermarket management project |
| `DAY 12 (Shopping Cart with Billing)` | Shopping cart and billing project |
| `DAY 13 JDBC (Student Management System)` | JDBC-based student management project |
| `DAY 20` | Advanced training exercises |
| `Shopping Cart` | Shopping cart application |

> Add programs to the relevant day folder as the trainer covers each topic. Confirm exact assignment requirements before implementing project features.

## Run Java programs

Check the installed JDK:

```bash
java -version
javac -version
```

For a simple single-file program:

```bash
javac Main.java
java Main
```

For multi-file projects, follow the instructions in that folder's README.

## Contribution guidelines

1. Pull the latest changes before starting work.
2. Work in the folder assigned for the relevant training day.
3. Use meaningful class, method, and variable names.
4. Include problem statements and sample output for exercises.
5. Compile and test code before committing.
6. Never commit passwords, API keys, database credentials, or personal information.
7. Keep commits small and focused.

## Suggested commit messages

```text
Day 01: add basic input and output exercises
Day 07: add inheritance examples
Project: implement student CRUD using JDBC
Docs: update setup instructions
```

## Team workflow

- Use descriptive branches for substantial changes, for example `feature/day-07-inheritance`.
- Review changes before merging when working as a team.
- Keep project-specific setup steps in the corresponding folder's README.

## Environment

The team should use the JDK version specified by the UPS trainer. Record the confirmed version here once provided.
