# Organize a benchmark's original and processed entries into a jsonl file.
from pathlib import Path
import os
import pandas as pd
import argparse

parser = argparse.ArgumentParser(
        prog='postprocessing',
        description="Organize a benchmark's original and augmented entries"
            "into a jsonl file")
parser.add_argument('metadata_path')
parser.add_argument('benchmark_path',
                    default='./Benchmarks/extracted_large_train')
parser.add_argument('output_jsonl')
args = parser.parse_args()

TRANSFORMED_PATH = Path(args.benchmark_path) / 'transformed'
META_PATH = Path(args.metadata_path)
OUTPUT_PATH = Path(args.output_jsonl)

print(f'reading {META_PATH}...')
with open(META_PATH, 'r') as f:
    meta_df = pd.read_json(f, lines=True)

df = pd.DataFrame(columns = [*meta_df.columns.values.tolist(), 'transformed',
                             'transform_type'])

for dir in os.listdir(TRANSFORMED_PATH):
    rule_id = int(dir.lstrip('_'))
    for file in os.listdir(TRANSFORMED_PATH / dir):
        code_id = int(file.lstrip('n').rstrip('.java'))
        with open(TRANSFORMED_PATH / dir / file) as f:
            transformed = f.read()
        entry = meta_df.iloc[code_id]
        new_entry = pd.DataFrame([[
            *entry,
            transformed,
            rule_id
            ]], columns=df.columns)
        df = pd.concat([df, new_entry], ignore_index=True)

df.to_json(OUTPUT_PATH, orient='records', lines=True)

# - repo: the owner/repo
# - func_name: the function or method name
# - language: the programming language
# - code: the part of the original_string that is code
# - docstring: the top-level comment or docstring, if it exists in the original string
# - transformed: transformed code by applying one of the transformations.
# - transform_type: which of the 6 transformations is applied to this 
