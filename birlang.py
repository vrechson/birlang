import subprocess
import pathlib
import sys

birl_path=pathlib.Path(__file__).parent.resolve()

if sys.argv[1] == "exec":
    cmd = ['java', '-jar',
    # compiler directory
    str(birl_path)+'/target/Birlang-1.0-SNAPSHOT-jar-with-dependencies.jar',
    # input path
    sys.argv[2],
    # output path
    sys.argv[3]
    ]
else:
    cmd = ["echo", "thanks for come"]

result = subprocess.run(cmd, stdout=subprocess.PIPE)
print(result.stdout.decode('UTF-8'))