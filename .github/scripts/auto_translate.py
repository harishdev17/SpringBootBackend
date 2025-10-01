#!/usr/bin/env python3
from pathlib import Path
import re, os
from deep_translator import GoogleTranslator

SRC = Path("docs/en")
OUT = Path("docs")
TARGETS = os.environ.get("TARGET_LANGS", "hi,es,fr").split(",")

FENCE_RE = re.compile(r'(```[\s\S]*?```)', re.MULTILINE)

def translate_text(text, lang):
    paragraphs = re.split(r'\n\s*\n', text)
    translator = GoogleTranslator(source='auto', target=lang)
    out=[]
    for p in paragraphs:
        if not p.strip():
            out.append(p)
            continue
        out.append(translator.translate(p))
    return "\n\n".join(out)

def protect_and_translate(text, lang):
    parts = FENCE_RE.split(text)
    out=[]
    for part in parts:
        if part.startswith("```"):
            out.append(part)
        else:
            out.append(translate_text(part, lang))
    return "".join(out)

def main():
    for lang in TARGETS:
        lang = lang.strip()
        if not lang: continue
        for src in SRC.rglob("*.md"):
            rel = src.relative_to(SRC)
            target_dir = OUT / lang / rel.parent
            target_dir.mkdir(parents=True, exist_ok=True)
            body = src.read_text(encoding="utf-8")
            translated = protect_and_translate(body, lang)
            (target_dir / rel.name).write_text(translated, encoding="utf-8")
            print("Wrote:", target_dir/rel.name)

if __name__ == "__main__":
    main()
