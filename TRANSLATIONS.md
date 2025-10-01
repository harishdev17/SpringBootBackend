# TRANSLATIONS

## Source of truth
- `docs/en/` is the canonical English documentation for the project.
- Keep `docs/en/` synced with any updates to the root README. The root README is a pointer.

## Path & filenames
- Use ISO-639-1 codes for language folders: `docs/hi/`, `docs/es/`, `docs/fr/`, etc.
- Keep the same filenames and relative paths as `docs/en/`.

## Workflow
1. Claim an issue "Translate docs/en/<path> → <lang>".
2. Create a branch: `feat/translate-<lang>-<short>`.
3. Add translator metadata at top of translated files:
   <!-- Translated by @username — status: draft — review: needed -->
4. Open a PR, add label `translation` (and `machine-translation` if machine-generated).
5. Request at least one native-speaker review before merging.

## Quality
- Do not edit files in `docs/en/` except to fix the English source.
- Preserve code blocks, YAML front matter, and links.
- Use `docs/GLOSSARY.md` for consistent terminology.

## Credits
- Add translator names to the top of each translated file (or maintain a `TRANSLATORS.md`).
