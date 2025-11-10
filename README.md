# MC Keycard Fabric

[![Build](https://github.com/BuddyHoli/mc-keycard-fabric/actions/workflows/ci.yml/badge.svg)](https://github.com/BuddyHoli/mc-keycard-fabric/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Ein umfassendes Keycard-System für Minecraft Fabric 1.21.1, das es ermöglicht, Keycards zu erstellen, geschützte Bereiche zu definieren und Blöcke/Entitäten zu sperren.

A comprehensive keycard system for Minecraft Fabric 1.21.1 that allows creating keycards, defining protected areas, and locking blocks/entities.

---

## 📖 Inhaltsverzeichnis / Table of Contents

- [🇩🇪 Deutsche Version](#-deutsche-version)
  - [Funktionen](#funktionen)
  - [Befehle](#befehle)
  - [Installation](#installation)
  - [Entwicklung](#entwicklung)
  - [Build-Anleitung](#build-anleitung)
- [🇬🇧 English Version](#-english-version)
  - [Features](#features)
  - [Commands](#commands)
  - [Installation](#installation-1)
  - [Development](#development)
  - [Build Instructions](#build-instructions)

---

## 🇩🇪 Deutsche Version

### Funktionen

**MC Keycard Fabric** ist ein Minecraft-Mod für Fabric, der ein flexibles Keycard-System bereitstellt:

- 🔑 **Keycards erstellen und verwalten**: Erstelle benannte Keycards mit individuellen Farben
- 🗺️ **Geschützte Bereiche**: Definiere 3D-Bereiche, die nur mit bestimmten Keycards betreten werden können
- 🔒 **Blöcke und Entitäten sperren**: Sperre einzelne Blöcke (Türen, Truhen, etc.) oder Entitäten (Rahmen, Rüstungsständer, etc.)
- 💾 **Persistenz**: Alle Daten werden automatisch gespeichert und beim Serverstart geladen
- ⚙️ **Konfigurierbar**: Umfangreiche Konfigurationsmöglichkeiten über `config/mc-keycard.toml`
- 🌍 **Multi-Dimension**: Unterstützt alle Dimensionen (Overworld, Nether, End)

### Befehle

Alle Befehle erfordern Operator-Rechte (OP-Level 2).

#### Keycard-Befehle

```
/keycard create <name> [color]     - Erstelle eine neue Keycard
/keycard give <player> <keycard>   - Gib einem Spieler eine Keycard
/keycard list                      - Liste alle verfügbaren Keycards auf
/keycard delete <keycard>          - Lösche eine Keycard
```

#### Bereichs-Befehle

```
/area create <name> <pos1> <pos2>  - Erstelle einen geschützten Bereich
/area addkey <area> <keycard>      - Füge Keycard-Zugriff zu einem Bereich hinzu
/area removekey <area> <keycard>   - Entferne Keycard-Zugriff von einem Bereich
/area list                         - Liste alle geschützten Bereiche auf
/area delete <area>                - Lösche einen geschützten Bereich
```

#### Sperr-Befehle

```
/lock <keycard>                    - Sperre den anvisierten Block/die Entität
/unlock                            - Entsperre den anvisierten Block/die Entität
```

### Installation

#### Voraussetzungen

- Minecraft Java Edition 1.21.1
- Fabric Loader 0.16.5 oder höher
- Fabric API 0.105.0+1.21.1 oder höher

#### Schritte

1. Installiere [Fabric Loader](https://fabricmc.net/use/)
2. Lade die neueste Version von [Fabric API](https://modrinth.com/mod/fabric-api) herunter
3. Lade die neueste Version von MC Keycard Fabric aus den [Releases](https://github.com/BuddyHoli/mc-keycard-fabric/releases) herunter
4. Platziere beide JAR-Dateien im `mods`-Ordner deines Minecraft-Verzeichnisses
5. Starte Minecraft mit dem Fabric-Profil

### Entwicklung

#### Projektstruktur

```
mc-keycard-fabric/
├── .github/workflows/     # CI/CD-Workflows
├── config/                # Beispiel-Konfiguration
├── gradle/                # Gradle Wrapper
├── src/
│   └── main/
│       ├── java/          # Java-Quellcode
│       │   └── com/buddyholi/keycard/
│       │       ├── KeycardMod.java          # Hauptklasse
│       │       ├── command/                  # Befehlsimplementierungen
│       │       └── data/                     # Datenmodelle
│       └── resources/     # Ressourcen
│           ├── fabric.mod.json              # Mod-Metadaten
│           ├── pack.mcmeta                  # Resource Pack-Metadaten
│           └── assets/mc-keycard-fabric/
│               └── lang/                    # Übersetzungen
├── build.gradle           # Gradle-Build-Konfiguration
├── gradle.properties      # Projekt-Eigenschaften
└── settings.gradle        # Gradle-Einstellungen
```

#### Nächste Schritte für Entwickler

Dieses Projekt ist aktuell ein **Skeleton** mit Platzhaltern. Die folgenden Funktionen müssen noch implementiert werden:

1. **Befehlsargumente**: Vollständige Argumente für alle Commands (StringArgumentType, EntityArgumentType, BlockPosArgumentType, etc.)
2. **Keycard-Item**: Custom Item für Keycards mit NBT-Daten
3. **Event-Handler**: Interaktions-Events für geschützte Bereiche und gesperrte Items
4. **Persistenz**: Laden/Speichern von Daten in JSON- oder NBT-Format
5. **Konfiguration**: Laden der `mc-keycard.toml`-Datei mit einer Config-Library
6. **Tests**: Unit- und Integration-Tests für alle Komponenten

### Build-Anleitung

#### Lokal bauen

1. **Repository klonen**:
   ```bash
   git clone https://github.com/BuddyHoli/mc-keycard-fabric.git
   cd mc-keycard-fabric
   ```

2. **Build ausführen**:
   ```bash
   ./gradlew build
   ```

3. **JAR-Datei finden**:
   Die gebaute Mod-JAR befindet sich in `build/libs/mc-keycard-fabric-1.0.0-SNAPSHOT.jar`

#### Mit GitHub Actions bauen

Jeder Push oder Pull Request triggert automatisch einen Build:

1. Gehe zu [Actions](https://github.com/BuddyHoli/mc-keycard-fabric/actions)
2. Wähle den neuesten Workflow-Lauf
3. Lade das Artifact `mc-keycard-fabric` herunter

#### Development-Umgebung

```bash
# Minecraft Client starten (für Tests)
./gradlew runClient

# Minecraft Server starten (für Tests)
./gradlew runServer

# Code generieren (Access Widener, etc.)
./gradlew genSources
```

### Lizenz

Dieses Projekt ist unter der [MIT-Lizenz](LICENSE) lizenziert.

---

## 🇬🇧 English Version

### Features

**MC Keycard Fabric** is a Minecraft mod for Fabric that provides a flexible keycard system:

- 🔑 **Create and manage keycards**: Create named keycards with custom colors
- 🗺️ **Protected areas**: Define 3D regions that can only be accessed with specific keycards
- 🔒 **Lock blocks and entities**: Lock individual blocks (doors, chests, etc.) or entities (item frames, armor stands, etc.)
- 💾 **Persistence**: All data is automatically saved and loaded on server startup
- ⚙️ **Configurable**: Extensive configuration options via `config/mc-keycard.toml`
- 🌍 **Multi-dimension**: Supports all dimensions (Overworld, Nether, End)

### Commands

All commands require operator privileges (OP level 2).

#### Keycard Commands

```
/keycard create <name> [color]     - Create a new keycard
/keycard give <player> <keycard>   - Give a keycard to a player
/keycard list                      - List all available keycards
/keycard delete <keycard>          - Delete a keycard
```

#### Area Commands

```
/area create <name> <pos1> <pos2>  - Create a protected area
/area addkey <area> <keycard>      - Add keycard access to an area
/area removekey <area> <keycard>   - Remove keycard access from an area
/area list                         - List all protected areas
/area delete <area>                - Delete a protected area
```

#### Lock Commands

```
/lock <keycard>                    - Lock the targeted block/entity
/unlock                            - Unlock the targeted block/entity
```

### Installation

#### Requirements

- Minecraft Java Edition 1.21.1
- Fabric Loader 0.16.5 or higher
- Fabric API 0.105.0+1.21.1 or higher

#### Steps

1. Install [Fabric Loader](https://fabricmc.net/use/)
2. Download the latest version of [Fabric API](https://modrinth.com/mod/fabric-api)
3. Download the latest version of MC Keycard Fabric from the [Releases](https://github.com/BuddyHoli/mc-keycard-fabric/releases)
4. Place both JAR files in the `mods` folder of your Minecraft directory
5. Start Minecraft with the Fabric profile

### Development

#### Project Structure

```
mc-keycard-fabric/
├── .github/workflows/     # CI/CD workflows
├── config/                # Example configuration
├── gradle/                # Gradle wrapper
├── src/
│   └── main/
│       ├── java/          # Java source code
│       │   └── com/buddyholi/keycard/
│       │       ├── KeycardMod.java          # Main class
│       │       ├── command/                  # Command implementations
│       │       └── data/                     # Data models
│       └── resources/     # Resources
│           ├── fabric.mod.json              # Mod metadata
│           ├── pack.mcmeta                  # Resource pack metadata
│           └── assets/mc-keycard-fabric/
│               └── lang/                    # Translations
├── build.gradle           # Gradle build configuration
├── gradle.properties      # Project properties
└── settings.gradle        # Gradle settings
```

#### Next Steps for Developers

This project is currently a **skeleton** with placeholders. The following features need to be implemented:

1. **Command arguments**: Complete arguments for all commands (StringArgumentType, EntityArgumentType, BlockPosArgumentType, etc.)
2. **Keycard item**: Custom item for keycards with NBT data
3. **Event handlers**: Interaction events for protected areas and locked items
4. **Persistence**: Loading/saving data in JSON or NBT format
5. **Configuration**: Loading the `mc-keycard.toml` file with a config library
6. **Tests**: Unit and integration tests for all components

### Build Instructions

#### Building Locally

1. **Clone the repository**:
   ```bash
   git clone https://github.com/BuddyHoli/mc-keycard-fabric.git
   cd mc-keycard-fabric
   ```

2. **Run the build**:
   ```bash
   ./gradlew build
   ```

3. **Find the JAR file**:
   The built mod JAR is located at `build/libs/mc-keycard-fabric-1.0.0-SNAPSHOT.jar`

#### Building with GitHub Actions

Every push or pull request automatically triggers a build:

1. Go to [Actions](https://github.com/BuddyHoli/mc-keycard-fabric/actions)
2. Select the latest workflow run
3. Download the artifact `mc-keycard-fabric`

#### Development Environment

```bash
# Start Minecraft client (for testing)
./gradlew runClient

# Start Minecraft server (for testing)
./gradlew runServer

# Generate sources (Access Widener, etc.)
./gradlew genSources
```

### License

This project is licensed under the [MIT License](LICENSE).

---

## 🤝 Beiträge / Contributing

Beiträge sind willkommen! Bitte erstelle einen Pull Request oder öffne ein Issue für Vorschläge und Fehlerberichte.

Contributions are welcome! Please create a pull request or open an issue for suggestions and bug reports.

## 📞 Kontakt / Contact

- GitHub: [BuddyHoli](https://github.com/BuddyHoli)
- Issues: [GitHub Issues](https://github.com/BuddyHoli/mc-keycard-fabric/issues)