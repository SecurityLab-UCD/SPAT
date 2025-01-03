#!/bin/bash
# run.sh [-l LIBPATH] [TEST_ID [BENCHMARK_NAME]]
# Runs SPAT

while getopts "l:" opt; do
    case $opt in
    l)
        lib_path="${OPTARG}"
        shift 2
        ;;
	\?)
	    echo "Invalid option: -$OPTARG" >&2
	    ;;
    esac
done
if [[ "$#" -ge 3 ]]; then
    echo "Illegal number of parameters" >&2
    exit 1
fi

# See README#java18
lib_path=${lib_path:-"/usr/lib/jvm/java-18-openjdk-amd64/lib"}

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
test_id=${1:-0}

# Assume default directory structure
# Benchmarks
# └── "$benchmark_name"
#     ├── Original
#     └── transformed
benchmark_name=${2:-"Java250"}

java -jar ./artifacts/SPAT-linux.jar \
    "$test_id" \
    "./Benchmarks/$benchmark_name/Original" \
    "./Benchmarks/$benchmark_name/transformed/_$test_id" \
    "$lib_path"
