# birlang
A language developed to build gym workouts for compiler classes

# Building
```
$ docker build . -t birlang
```

# Running
```
docker run birlang exec input.acad workout.html
```

# Examples
```
nome: "Carlos O cria"
treino: "Aerobico"
treinador: "Eduardo Madurera"
data: 10/10/2021

TREINO
tipo: A % sempre uma letra maíuscula
SUPINO RETO, 3, 10, 60;
ABDOMINAL, 4, 20, 20;
END_TREINO

% XESQUE

TREINO
tipo: B
AGACHAMENTO, 3, 30, 60;
VOADOR COSTAS, 3, 30, 60;
END_TREINO
```

output:
![2](https://user-images.githubusercontent.com/6394077/143508959-fd0b2725-77a9-45ea-a9e2-a993a844a406.PNG)
