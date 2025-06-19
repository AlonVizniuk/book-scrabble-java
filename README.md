# Book Scrabble Java Project

## Description

A server-client Scrabble-like game written in Java that demonstrates custom data
structures, dictionary management with Bloom filters and caching, and basic
socket programming.
 
It is organized into several packages:  
- `board/`  
- `dictionary/`  
- `server/`  
- `src/` (contains the main entry point)

## Features

- **Scrabble board logic** (15 × 15 grid, placement and scoring rules)
- **Tile bag** with standard Scrabble letter distribution and scores
- **Dictionary management**
  - Multiple dictionary files
  - Bloom filter for quick negative look-ups
  - LRU and LFU caches for repeated queries
- **Multi-threaded server / client**
  - Clients send `QUERY` or `CHALLENGE` requests
- **Test harness** in `MainTrain.java`


---

## Getting Started

### Prerequisites

- Java JDK 8 (or higher)

### Compilation

**Linux/macOS**

```bash
mkdir -p bin
javac -d bin $(find src -name "*.java")
```

**Windows (Command Prompt)**

```cmd
mkdir bin
for /R src %f in (*.java) do javac -d bin %f
```

Or compile with an IDE such as IntelliJ IDEA or Eclipse.

### Running

```bash
cd bin
java MainTrain
```

This starts the server on a random port, runs board and dictionary tests,
and simulates client requests.

---

## Example Output

```
Server is up on port 52341
Placing word 'HELLO' at (7,7): success
Client: QUERY 'apple'    -> true
Client: CHALLENGE 'asdf' -> true (invalid word)
```

---

## How It Works

- **Server (`MyServer`)**
  - Listens on a random port
  - Handles each client in a separate thread (`BookScrabbleHandler`)
- **Client request formats**
  - `QUERY word file1 file2 ...`
  - `CHALLENGE word file1 file2 ...`
- **Dictionary lookup path**
  1. Bloom filter
  2. LRU and LFU caches
  3. File search via `IOSearcher`
- **Board (`Board.java`)**
  - Validates placement, computes scores, tracks bag state

---

## Customization

- Add dictionary `.txt` files and reference them in `MainTrain.java`.
- Modify `Board.java` or `Tile.java` to change rules or scoring.
- Build a GUI front-end that talks to the server.

---

Make sure your `.gitignore` includes:
```
/bin/
*.class
.vscode/
.DS_Store
Thumbs.db
```

## Notes

This project is for educational and demonstration purposes; it is not intended
for production use.

---

## License

Provided “as is” without warranty of any kind.
