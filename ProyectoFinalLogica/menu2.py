import tkinter as tk
import webbrowser
import subprocess
import os

def abrir_programa1():
    try:
        print("Abriendo p1.html...")
        subprocess.Popen(["start", "p1.html"], shell=True)
        ventana.destroy()
    except Exception as e:
        print("Error al abrir p1.html:", e)

def abrir_programa2():
    try:
        print("Abriendo p2.py...")
        subprocess.Popen(["python", "p2.py"])
        ventana.destroy()
    except Exception as e:
        print("Error al abrir p2.py:", e)

def abrir_programa3():
    try:
        print("Abriendo p3.py...")
        subprocess.Popen(["python", "p3.py"])
        ventana.destroy()
    except Exception as e:
        print("Error al abrir p3.py:", e)

def abrir_programa4():
    try:
        print("Abriendo p4.py...")
        subprocess.Popen(["python", "p4.py"])
        ventana.destroy()
    except Exception as e:
        print("Error al abrir p4.py:", e)

ventana = tk.Tk()
ventana.geometry('500x500')
ventana.config(background="gray71")
ventana.title("Menú de Programas")

# Crear botones
boton1 = tk.Button(ventana, text="Tablas de verdad", command=abrir_programa1, background="light grey")
boton2 = tk.Button(ventana, text="Operaciones sobre conjuntos", command=abrir_programa2, background="light grey")
boton3 = tk.Button(ventana, text="Sucesiones e inducción", command=abrir_programa3, background="light grey")
boton4 = tk.Button(ventana, text="Relaciones y funciones", command=abrir_programa4, background="light grey")

# Organizar botones usando grid
boton1.grid(row=0, column=0, pady=5, padx=5)
boton2.grid(row=1, column=0, pady=5, padx=5)
boton3.grid(row=2, column=0, pady=5, padx=5)
boton4.grid(row=3, column=0, pady=5, padx=5)

ventana.mainloop()


