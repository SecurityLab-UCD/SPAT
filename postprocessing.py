# Organize a benchmark's original and processed entries into a jsonl file.
from pathlib import Path
from enum import StrEnum
from tqdm import tqdm
import os
import pandas as pd
import json
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

def jsonl_to_df(path, chunksize=1000):
    with open(path, 'r') as file:
        # Count total lines in the file
        total_lines = sum(1 for _ in file)

    with open(path, 'r') as file, tqdm(total=total_lines, desc=f'reading {path}') as pbar:
        chunks = []
        for chunk in pd.read_json(file, lines=True, chunksize=chunksize):
            chunks.append(chunk)
            pbar.update(chunksize)
        return pd.concat(chunks, ignore_index=True)


id_to_name = [
    'LocalVarRenaming',
    'For2While',
    'While2For',
    'ReverseIfElse',
    'SingleIF2ConditionalExp',
    'ConditionalExp2SingleIF',
    'PP2AddAssignment',
    'AddAssignemnt2EqualAssignment',
    'InfixExpressionDividing',
    'IfDividing',
    'StatementsOrderRearrangement',
    'LoopIfContinue2Else',
    'VarDeclarationMerging',
    'VarDeclarationDividing',
    'SwitchEqualSides',
    'SwitchStringEqual',
    'PrePostFixExpressionDividing',
    'Case2IfElse',
]
class AugType(StrEnum):
    LocalVarRenaming = 'LocalVarRenaming'
    For2While = 'For2While'
    While2For = 'While2For'
    ReverseIfElse = 'ReverseIfElse'
    SingleIF2ConditionalExp = 'SingleIF2ConditionalExp'
    ConditionalExp2SingleIF = 'ConditionalExp2SingleIF'
    PP2AddAssignment = 'PP2AddAssignment'
    AddAssignemnt2EqualAssignment = 'AddAssignemnt2EqualAssignment'
    InfixExpressionDividing = 'InfixExpressionDividing'
    IfDividing = 'IfDividing'
    StatementsOrderRearrangement = 'StatementsOrderRearrangement'
    LoopIfContinue2Else = 'LoopIfContinue2Else'
    VarDeclarationMerging = 'VarDeclarationMerging'
    VarDeclarationDividing = 'VarDeclarationDividing'
    SwitchEqualSides = 'SwitchEqualSides'
    SwitchStringEqual = 'SwitchStringEqual'
    PrePostFixExpressionDividing = 'PrePostFixExpressionDividing'
    Case2IfElse = 'Case2IfElse'
    @classmethod
    def from_id(cls, id):
        return cls(id_to_name[id])

meta_df = jsonl_to_df(META_PATH)

try:
    os.remove(OUTPUT_PATH)
except Exception as _:
    pass

for dir in tqdm(os.listdir(TRANSFORMED_PATH), desc=f"Parsing rules"):
    rule_id = int(dir.lstrip('_'))
    for file in tqdm(os.listdir(TRANSFORMED_PATH / dir)):
        code_id = int(file.lstrip('n').rstrip('.java'))
        with open(TRANSFORMED_PATH / dir / file) as f:
            transformed = f.read()
        meta = meta_df.iloc[code_id]
        entry = {
            **meta,
            "transformed": transformed,
            'aug_type': AugType.from_id(rule_id)
        }
        with open(OUTPUT_PATH, 'a') as f:
            f.write(f'{json.dumps(entry)}\n')
