# Get custom info
dirToLint=$(git config hooks.lintTargetDirectory)
lintArgs=$(git config hooks.lintArgs)

# If user has not defined a preferred directory to lint against, make it .
if [ -z "$dirToLint"]
  then
  dirToLint="."
fi

# Perform lint check

echo "Performing pre-push lint check of ""$dirToLint"
lint $lintArgs "--exitcode" $dirToLint
lintStatus=$?

if [ $lintStatus -ne 0 ]
then
  echo "Lint failure."
  exit 1
fi

exit $lintStatus