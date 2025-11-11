# KeyCard Mod for Minecraft Fabric

## English

### Overview
KeyCard Mod is a Minecraft mod for Java Edition 1.21.10 using Fabric. It introduces keycards and optional copies, granting access to doors, containers, and protected areas. Original keycards allow the owner to control protected objects and blocks, while additional cards can have defined permissions.

### Features
- Manage protected doors and containers with keycards
- Grant/revoke access and area permissions to users
- Full command set for Admins/OP and for players
- Configurable max keycard copies, areas, items
- Autodoor feature (proximity-based)
- Support for other mod items (via config)

### Usage/Commands (Summary)
**Admin/OP commands:**
- `/keycard <owner> create` — Create keycard for user
- `/keycard <owner> delete` — Delete keycard + copies  
- `/keycard <owner> set cardarea [AreaName]` — Mark named area  
- `/keycard <owner> del cardarea [AreaName]` — Delete named area

**Player commands:**
- `/keycard lock` / `/keycard unlock` — Lock/unlock item
- `/keycard <user> create` — Make copy for user
- `/keycard lock area [AreaName]` — Lock area on copy, etc.

See below for full command reference and hints.

### Configuration
Edit `config/config.toml` for:
- `max_copies`
- `max_areas_per_keycard`
- `max_items_per_keycard`
- List of item IDs for compatibility

#### Example:
```toml
max_copies = 8
max_areas_per_keycard = 4
max_items_per_keycard = 40
additional_items = ["modid:special_door", "modid:secure_chest"]
```

### Developer Quickstart

#### Requirements
- Java 17+
- Gradle, Fabric Loom, Fabric API

#### Build Instructions
```bash
./gradlew build
```

Output .jar will be in `build/libs/`.

---

## Deutsch

### Übersicht
KeyCard Mod ist ein Minecraft Mod für Java Version 1.21.10 (Fabric Loader). Er integriert Keycards und deren Kopien, mit denen Türen, Behälter und Bereiche geschützt werden und der Besitzer Zugriffsrechte vergeben kann.

### Features
- Türen und Behälter schützen/sperren/entsperren
- Bereiche definieren und Berechtigungen zuweisen
- Zusätzliche Keycards für andere Spieler mit individuellen Rechten
- Admin- und Spieler-Kommandos
- Konfigurierbare Limits (Karten, Bereiche, Items)
- Autodoor-Funktion (Tür öffnen bei Nähe)
- Mod-Kompatibilität über config

### Beispiel-Kommandos

**Admin-Befehle:**
- `/keycard <owner> create` — Keycard erstellen
- `/keycard <owner> delete` — Keycard und Kopien löschen
- `/keycard <owner> set cardarea [Bereichsname]` — Bereich setzen
- `/keycard <owner> del cardarea [Bereichsname]` — Bereich löschen

**Spieler-Befehle:**
- `/keycard lock` / `/keycard unlock` — Item (Tür/Behälter) sperren/entz.
- `/keycard <user> create` — Kopie für Spieler erstellen
- `/keycard lock area [Bereichsname]` — Bereich sperren

Weitere Befehle siehe im ausführlichen Befehlsbereich weiter unten.

### Konfiguration

Datei: `config/config.toml`
- `max_copies`
- `max_areas_per_keycard`
- `max_items_per_keycard`
- Zusätzliche Items für Mod-Unterstützung

#### Beispiel:
```toml
max_copies = 8
max_areas_per_keycard = 4
max_items_per_keycard = 40
additional_items = ["modid:special_door", "modid:secure_chest"]
```

### Entwicklung & Build

#### Voraussetzungen
- Java 17+
- Gradle, Fabric Loom, Fabric API

#### Build:
```bash
./gradlew build
```

Das gebaute Jar findest du unter `build/libs/`.

---

### Vollständiges Befehls- und Funktionsreferenz

[Hier optional deine ausführliche Beschreibung und alle Commands einfügen – siehe erster Prompt.]