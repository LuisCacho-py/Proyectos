#include <stdio.h>
#include <stdlib.h>

// Estructura para los jugadores
typedef struct {
    char nombre[20];
    char simbolo; // 'B' para blancas, 'N' para negras
} Jugador;

//imprime la plantilla de las posiciones
void plant() {
    printf("COORDENADAS\n");
    printf("1,1|1,2|1,3|1,4|1,5|1,6\n");
    printf("---|---|---|---|---|---\n");
    printf("2,1|2,2|2,3|2,4|2,5|2,6\n");
    printf("---|---|---|---|---|---\n");
    printf("3,1|3,2|3,3|3,4|3,5|3,6\n");
    printf("---|---|---|---|---|---\n");
    printf("4,1|4,2|4,3|4,4|4,5|4,6\n");
    printf("---|---|---|---|---|---\n");
    printf("5,1|5,2|5,3|5,4|5,5|5,6\n");
    printf("---|---|---|---|---|---\n");
    printf("6,1|6,2|6,3|6,4|6,5|6,6\n");
    printf("---|---|---|---|---|---\n");
    printf("\n");
    printf("OTHELLO WHIRL");
}

// Funcion para mostrar el menu principal
void menu() {
    printf("\nBienvenido a Othello Whirl\n");
    printf("Juego creado por: Natalia Jimena Correa & Luis Gerardo Cacho\n");

    printf("\nSelecciona una de las opciones: \n");
    printf("1: Empezar el juego\n");
    printf("2: Cerrar juego\n");
    printf("Opcion: ");
}

// Funcion para inicializar el tablero con espacios vacíos
void initablero(char **tablero) {
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            tablero[i][j] = ' ';
        }
    }
}

// Funcion para mostrar el tablero en pantalla
void showtab(char **tablero) {
    printf("\n");
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            printf("| %c ", tablero[i][j]);
        }
        printf("|\n");
        printf("-------------------------\n");
    }
    
    printf("\n");
}


void limpiarBuffer() { while (getchar() != '\n'); } // Limpia buffer de entrada

// Funcion para que un jugador coloque una canica en el tablero
void canica(char **tablero, Jugador jugador) {
    int fila, columna;  
    while (1) {
        printf("%s (%c), ingresa fila y columna (1-6): ", jugador.nombre, jugador.simbolo);
        if (scanf("%d %d", &fila, &columna) == 2 && fila >= 1 && fila <= 6 && columna >= 1 && columna <= 6 
            && tablero[fila-1][columna-1] == ' ') break;
        printf("Entrada invalida o posicion ocupada. Intenta de nuevo.\n");
        limpiarBuffer(); // Limpia buffer si hay error
    }
    tablero[fila-1][columna-1] = jugador.simbolo;
}

// Funcion para rotar un subtablero 3x3 (subtableros son numerados de 1 a 4)
void rotar(char **tablero, int subtablero, int sentido) {
    int inicio_fila = (subtablero <= 2) ? 0 : 3;
    int inicio_columna = (subtablero == 1 || subtablero == 3) ? 0 : 3;
    char temp[3][3];

    // Copiar subtablero a matriz temporal
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            temp[i][j] = tablero[inicio_fila + i][inicio_columna + j];
        }
    }

    // Rota el subtablero según el sentido (1: antihorario, -1: horario)
    if (sentido == 1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[inicio_fila + i][inicio_columna + j] = temp[j][2 - i];
            }
        }
    } else if (sentido == -1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[inicio_fila + i][inicio_columna + j] = temp[2 - j][i];
            }
        }
    }
}

// Funcion para verificar si hay un ganador con 5 canicas en línea
int verificacion(char **tablero, char simbolo) {
    // Verifica filas, columnas y diagonales
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
            // Verifica fila
            if (j <= 1 && tablero[i][j] == simbolo && tablero[i][j + 1] == simbolo && tablero[i][j + 2] == simbolo && tablero[i][j + 3] == simbolo && tablero[i][j + 4] == simbolo) {
                return 1;
            }
            // Verifica columna
            if (i <= 1 && tablero[i][j] == simbolo && tablero[i + 1][j] == simbolo && tablero[i + 2][j] == simbolo && tablero[i + 3][j] == simbolo && tablero[i + 4][j] == simbolo) {
                return 1;
            }
            // Verifica diagonal 1
            if (i <= 1 && j <= 1 && tablero[i][j] == simbolo && tablero[i + 1][j + 1] == simbolo && tablero[i + 2][j + 2] == simbolo && tablero[i + 3][j + 3] == simbolo && tablero[i + 4][j + 4] == simbolo) {
                return 1;
            }
            // Verifica diagonal 2
            if (i >= 4 && j <= 1 && tablero[i][j] == simbolo && tablero[i - 1][j + 1] == simbolo && tablero[i - 2][j + 2] == simbolo && tablero[i - 3][j + 3] == simbolo && tablero[i - 4][j + 4] == simbolo) {
                return 1;
            }
        }
    }
    return 0;
}

int main() {
    int option;
    char **tablero;

    // Asigna memoria para el tablero 6x6
    tablero = (char **)malloc(6 * sizeof(char *));
    for (int i = 0; i < 6; i++) {
        tablero[i] = (char *)malloc(6 * sizeof(char));
    }

    // Jugadores
    Jugador jugador1 = {"Jugador 1", 'B'};
    Jugador jugador2 = {"Jugador 2", 'N'};
    Jugador *jugador_actual = &jugador1;

    do {
        menu();
        scanf("%d", &option);
        printf("\n");

        if (option == 1) {
            initablero(tablero);

            int turno = 0;
            while (1) {
                plant();
                showtab(tablero);
                canica(tablero, *jugador_actual);

                // Pregunta si se quiere rotar un subtablero
                int subtablero, sentido;
                printf("Deseas rotar un subtablero? (1-4), ingresa 0 para no rotar: ");
                scanf("%d", &subtablero);
                if (subtablero != 0) {
                    printf("Indica el sentido de rotacion (1: antihorario, -1: horario): ");
                    scanf("%d", &sentido);
                    rotar(tablero, subtablero, sentido);
                }

                // Verifica si hay un ganador
                if (verificacion(tablero, jugador_actual->simbolo)) {
                    showtab(tablero);
                    printf("¡%s ha ganado!\n", jugador_actual->nombre);
                    break;
                }

                // Cambia de jugador
                turno++;
                jugador_actual = (turno % 2 == 0) ? &jugador1 : &jugador2;

                //Borra la terminal para que no se vea fea la interfaz
                system("cls");
            }
        }

    } while (option != 2);

    // Libera memoria del tablero
    for (int i = 0; i < 6; i++) {
        free(tablero[i]);
    }
    free(tablero);

    printf("Gracias por jugar!\n");
    return 0;
}
