from pathlib import Path
from tqdm import tqdm
import pandas as pd

path = Path('final.jsonl')
chunksize = 10000

with open(path, 'r') as file:
    # Count total lines in the file
    total_lines = sum(1 for _ in file)

with open(path, 'r') as file, tqdm(total=total_lines, desc=f'reading {path}') as pbar:
    # Iterate through chunks with tqdm progress bar
    chunks = []
    for chunk in pd.read_json(file, lines=True, chunksize=chunksize):
        chunks.append(chunk)
        pbar.update(chunksize)
    df = pd.concat(chunks, ignore_index=True)

def inspect_entry(id):
    entry = df.iloc[id]
    print(entry)
    print(entry.code)
    print(entry.transformed)
