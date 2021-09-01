# Installation

## Build AnyBURL (License: https://web.informatik.uni-mannheim.de/AnyBURL/)

1. Create folder `build` in folder `AnyBURL-RE`
2. Compile with `javac de/unima/ki/anyburl/*.java -d build`
3. Package with `jar cfv AnyBURL-RE.jar -C build .`

## Build SAFRAN

1. Create directory `build` in folder `SAFRAN`
2. Download and extract boost 1.76.0 to folder `SAFRAN`
   1. Windows: https://boostorg.jfrog.io/artifactory/main/release/1.76.0/source/boost_1_76_0.zip
   2. Unix: https://boostorg.jfrog.io/artifactory/main/release/1.76.0/source/boost_1_76_0.tar.gz
3. Have cmake installed (> 9.6.0)
4. Run `cmake ../` from newely created folder `build`
5. Run `make`

## Install openbiolink

```bash
pip install torch openbiolink
```

# Training

Run from project root:

```bash
python3 train.py
```

# Evaluation

Run from project root:

```bash
python3 save_test_submission.py
```



