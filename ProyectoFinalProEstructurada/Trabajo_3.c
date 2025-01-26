#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct Usuario {
    int ID;
    char nombre[50];
    char apellido[50];
};

struct Calificacion {
    int ID;
    char materia[50];
    float calificacion;
};

void cargar_usuarios(struct Usuario usuarios[], int *numUsuarios, char *archivoUsuarios) {
    FILE *archivo = fopen(archivoUsuarios, "r");
    if (archivo == NULL) {
        printf("Error al abrir el archivo de usuarios.\n");
        exit(1);
    }

    char linea[100];
    while (fgets(linea, sizeof(linea), archivo) != NULL) {
        sscanf(linea, "%d ,%[^,],%[^,]", &usuarios[*numUsuarios].ID, usuarios[*numUsuarios].nombre, usuarios[*numUsuarios].apellido);
        (*numUsuarios)++;
    }

    fclose(archivo);
}

void cargar_calificaciones(struct Calificacion calificaciones[], int *numCalificaciones, char *archivoCalificaciones) {
    FILE *archivo = fopen(archivoCalificaciones, "r");
    if (archivo == NULL) {
        printf("Error al abrir el archivo de calificaciones.\n");
        exit(1);
    }

    char linea[100];
    while (fgets(linea, sizeof(linea), archivo) != NULL) {
        sscanf(linea, "%d ,%[^,],%f", &calificaciones[*numCalificaciones].ID, calificaciones[*numCalificaciones].materia, &calificaciones[*numCalificaciones].calificacion);
        (*numCalificaciones)++;
    }

    fclose(archivo);
}

void mostrar_usuarios(struct Usuario usuarios[], int numUsuarios) {
    printf("\nListado de usuarios:\n\n");
    for (int i = 0; i < numUsuarios; i++) {
        printf("ID: %d, Nombre: %s, Apellido: %s\n", usuarios[i].ID, usuarios[i].nombre, usuarios[i].apellido);
    }
}

void mostrar_calificaciones(struct Usuario usuarios[], int numUsuarios, struct Calificacion calificaciones[], int numCalificaciones, int ID) {
    printf("\nCalificaciones del usuario con ID %d:\n", ID);
    for (int i = 0; i < numUsuarios; i++) {
        if (usuarios[i].ID == ID) {
            printf("\nNombre: %s %s\n", usuarios[i].nombre, usuarios[i].apellido);
            printf("Materia    \tCalificación\n");
            for (int j = 0; j < numCalificaciones; j++) {
                if (calificaciones[j].ID == ID) {
                    printf("%-10s \t%.2f\n", calificaciones[j].materia, calificaciones[j].calificacion);
                }
            }
            return;
        }
    }
    printf("\nNo se encontraron calificaciones para el usuario con ID %d\n", ID);
}

int main(int argc, char *argv[]) {
    if (argc != 3) {
        printf("Uso: %s <archivo_usuarios.csv> <archivo_calificaciones.csv>\n", argv[0]);
        return 1;
    }

    struct Usuario usuarios[100];
    struct Calificacion calificaciones[100];
    int numUsuarios = 0;
    int numCalificaciones = 0;

    cargar_usuarios(usuarios, &numUsuarios, argv[1]);
    cargar_calificaciones(calificaciones, &numCalificaciones, argv[2]);

    int opcion;
    do{
        printf("\nMenú:\n");
        printf("1. Listado de usuarios registrados con ID\n");
        printf("2. Listado de calificaciones\n");
        printf("3. Salir\n");
        printf("Seleccione una opción: ");
        scanf("%d", &opcion);
        int ID;
        switch (opcion) {
            case 1:
                mostrar_usuarios(usuarios, numUsuarios);
                break;
            case 2:
                printf("Ingrese el ID del usuario: ");
                scanf("%d", &ID);
                mostrar_calificaciones(usuarios, numUsuarios, calificaciones, numCalificaciones, ID);
                break;
            case 3:
                printf("\nSaliendo del programa...\n");
                break;
            default:
                printf("Opción no válida. Por favor, seleccione una opción válida.\n");
        }
    }while (opcion != 3);

    return 0;
}
