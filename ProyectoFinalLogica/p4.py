import tkinter as tk
from tkinter import messagebox
import subprocess

def volver():
    root.destroy()  # Cerrar la ventana del menú
    subprocess.Popen(["python", "menu2.py"])

def es_reflexiva(relacion):
    for elemento in set([x[0] for x in relacion]):
        if (elemento, elemento) not in relacion:
            return False
    return True

def es_simetrica(relacion):
    for a, b in relacion:
        if (b, a) not in relacion:
            return False
    return True

def es_transitiva(relacion):
    for a, b in relacion:
        for c, d in relacion:
            if b == c and (a, d) not in relacion:
                return False
    return True

def obtener_dominio_y_codominio(relacion):
    dominio = set([x[0] for x in relacion])
    codominio = set([x[1] for x in relacion])
    return dominio, codominio

def es_funcion(relacion):
    dominio, _ = obtener_dominio_y_codominio(relacion)
    for elem in dominio:
        imagenes = [x[1] for x in relacion if x[0] == elem]
        if len(imagenes) != len(set(imagenes)):
            return False
    return True

def calcular_propiedades():
    relacion = []
    for par in entries:
        par_text = par.get()
        if par_text.strip() == "":
            continue
        try:
            par_tupla = tuple(map(int, par_text.split(',')))
            relacion.append(par_tupla)
        except ValueError:
            messagebox.showerror("Error", "Por favor, ingresa pares ordenados válidos.")
            return

    reflexividad.set(str(es_reflexiva(relacion)))
    simetria.set(str(es_simetrica(relacion)))
    transitividad.set(str(es_transitiva(relacion)))

    dominio, codominio = obtener_dominio_y_codominio(relacion)
    dominio_str = ', '.join(map(str, dominio))
    codominio_str = ', '.join(map(str, codominio))
    dominio_var.set(dominio_str)
    codominio_var.set(codominio_str)

    funcion.set(str(es_funcion(relacion)))

# Crear la ventana principal
root = tk.Tk()
root.title("Propiedades de una Relación")
root.geometry('300x500')
root.config(background="gray71")

# Crear y posicionar los widgets
entries = []
for i in range(8):
    tk.Label(root, text=f"Par {i+1}:", background="light grey").grid(row=i+1, column=0, padx=5, pady=5)
    entry = tk.Entry(root)
    entry.grid(row=i+1, column=1, padx=5, pady=5)
    entries.append(entry)

tk.Button(root, text="Calcular", command=calcular_propiedades, background="skyblue4").grid(row=9, columnspan=1, padx=5, pady=10)

reflexividad = tk.StringVar()
simetria = tk.StringVar()
transitividad = tk.StringVar()
dominio_var = tk.StringVar()
codominio_var = tk.StringVar()
grafo_var = tk.StringVar()
funcion = tk.StringVar()

tk.Button(root, text="Volver al menú", background="sea green", fg="white", command=volver).grid(row=0, columnspan=1, pady=5, padx=10)

tk.Label(root, text="Reflexividad:").grid(row=10, column=0, sticky='e')
tk.Label(root, textvariable=reflexividad, background="lightblue2").grid(row=10, column=1, sticky='w')
tk.Label(root, text="Simetría:").grid(row=11, column=0, sticky='e')
tk.Label(root, textvariable=simetria, background="lightblue2").grid(row=11, column=1, sticky='w')
tk.Label(root, text="Transitividad:").grid(row=12, column=0, sticky='e')
tk.Label(root, textvariable=transitividad, background="lightblue2").grid(row=12, column=1, sticky='w')
tk.Label(root, text="Dominio:").grid(row=13, column=0, sticky='e')
tk.Label(root, textvariable=dominio_var, background="lightblue2").grid(row=13, column=1, sticky='w')
tk.Label(root, text="Codominio:").grid(row=14, column=0, sticky='e')
tk.Label(root, textvariable=codominio_var, background="lightblue2").grid(row=14, column=1, sticky='w')

tk.Label(root, text="Función:").grid(row=16, column=0, sticky='e')
tk.Label(root, textvariable=funcion, background="lightblue2").grid(row=16, column=1, sticky='w')

root.mainloop()
