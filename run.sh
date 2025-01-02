#!/bin/bash
# run.sh 

lib_path="/usr/lib/jvm/java-18-openjdk-amd64/lib"

# 0. LocalVarRenaming:
# 1. For2While
# 2. While2For
# 3. ReverseIfElse
# 4. SingleIF2ConditionalExp
# 5. ConditionalExp2SingleIF
# 6. PP2AddAssignment
# 7. AddAssignemnt2EqualAssignment
# 8. InfixExpressionDividing
# 9. IfDividing
# 10. StatementsOrderRearrangement
# 11. LoopIfContinue2Else
# 12. VarDeclarationMerging
# 13. VarDeclarationDividing
# 14. SwitchEqualSides
# 15. SwitchStringEqual
# 16. PrePostFixExpressionDividing
# 17. Case2IfElse
test_id=0

# Assume default directory structure
# Benchmarks
# └── "$benchmark_name"
#     ├── Original
#     └── transformed
benchmark_name="Java250"

java -jar ./artifacts/SPAT-linux.jar \
    "$test_id" \
    "./Benchmarks/$benchmark_name/Original" \
    "./Benchmarks/$benchmark_name/transformed/_$test_id" \
    "$lib_path"
