import tkinter as tk
from tkinter import filedialog, messagebox
import subprocess

def volver():
    root.destroy()  # Cerrar la ventana del menú
    subprocess.Popen(["python", "menu2.py"])

def reset_entries():
    entry1.delete(0, tk.END)
    entry2.delete(0, tk.END)
    entry3.delete(0, tk.END)
    entry4.delete(0, tk.END)
    result_label.config(text="Resultado:")

def read_file(filename):
    try:
        with open(filename, 'r') as file:
            return file.read().strip().split(',')
    except FileNotFoundError:
        messagebox.showerror("Error", f"No se pudo encontrar el archivo '{filename}'.")

def calculate_operation(operation):
    selected_sets = []
    if var1.get():
        selected_sets.append(read_file(entry1.get()))
    if var2.get():
        selected_sets.append(read_file(entry2.get()))
    if var3.get():
        selected_sets.append(read_file(entry3.get()))
    if var4.get():
        selected_sets.append(read_file(entry4.get()))
    if len(selected_sets) < 2:
        messagebox.showerror("Error", "Debe seleccionar al menos dos conjuntos para realizar la operación.")
    else:
        if operation == "Unión":
            result = set(selected_sets[0]).union(*selected_sets[1:])
            result_label.config(text=f"Resultado Unión: {result}")
        elif operation == "Intersección":
            result = set(selected_sets[0]).intersection(*selected_sets[1:])
            result_label.config(text=f"Resultado Intersección: {result}")
        elif operation == "Diferencia":
            result = set(selected_sets[0]).difference(*selected_sets[1:])
            result_label.config(text=f"Resultado Diferencia: {result}")
        elif operation == "Diferencia Simétrica":
            result = set(selected_sets[0]).symmetric_difference(*selected_sets[1:])
            result_label.config(text=f"Resultado Diferencia Simétrica: {result}")

def select_file(entry):
    filename = filedialog.askopenfilename(filetypes=[("Archivo de texto", "*.txt")])
    if filename:
        entry.delete(0, tk.END)
        entry.insert(0, filename)

root = tk.Tk()
root.title("Calculadora de Teoría de Conjuntos")
root.config(background="gray71")
root.geometry('470x350')


tk.Button(root, text="Volver al menu",command=volver, background="sea green",fg="white").grid(row=0,columnspan=1, pady=5,padx=10)

label1 = tk.Label(root, text="Conjunto A:",background="lightblue2")
label1.grid(row=1, column=0)
entry1 = tk.Entry(root)
entry1.grid(row=1, column=1)
var1 = tk.BooleanVar()
check_button1 = tk.Checkbutton(root, text="Seleccionar", variable=var1)
check_button1.grid(row=1, column=2)
browse_button1 = tk.Button(root, text="Buscar", command=lambda: select_file(entry1))
browse_button1.grid(row=1, column=3)

label2 = tk.Label(root, text="Conjunto B:",background="lightblue2")
label2.grid(row=2, column=0)
entry2 = tk.Entry(root)
entry2.grid(row=2, column=1)
var2 = tk.BooleanVar()
check_button2 = tk.Checkbutton(root, text="Seleccionar", variable=var2)
check_button2.grid(row=2, column=2)
browse_button2 = tk.Button(root, text="Buscar", command=lambda: select_file(entry2))
browse_button2.grid(row=2, column=3)

label3 = tk.Label(root, text="Conjunto C:",background="lightblue2")
label3.grid(row=3, column=0)
entry3 = tk.Entry(root)
entry3.grid(row=3, column=1)
var3 = tk.BooleanVar()
check_button3 = tk.Checkbutton(root, text="Seleccionar", variable=var3)
check_button3.grid(row=3, column=2)
browse_button3 = tk.Button(root, text="Buscar", command=lambda: select_file(entry3))
browse_button3.grid(row=3, column=3)

label4 = tk.Label(root, text="Conjunto D:",background="lightblue2")
label4.grid(row=4, column=0)
entry4 = tk.Entry(root)
entry4.grid(row=4, column=1)
var4 = tk.BooleanVar()
check_button4 = tk.Checkbutton(root, text="Seleccionar", variable=var4)
check_button4.grid(row=4, column=2, padx=3)
browse_button4 = tk.Button(root, text="Buscar", command=lambda: select_file(entry4))
browse_button4.grid(row=4, column=3)


union_button = tk.Button(root, text="Unión", command=lambda: calculate_operation("Unión"), background= "skyblue4",fg="white")
union_button.grid(row=6, column=0,padx=0, pady=1)

intersection_button = tk.Button(root, text="Intersección", command=lambda: calculate_operation("Intersección"), background= "skyblue4",fg="white")
intersection_button.grid(row=7, column=0, padx=0, pady=1)

difference_button = tk.Button(root, text="Diferencia", command=lambda: calculate_operation("Diferencia"), background= "skyblue4",fg="white")
difference_button.grid(row=8,column=0,padx=0, pady=1)

symmetric_difference_button = tk.Button(root, text="Diferencia Simétrica", command=lambda: calculate_operation("Diferencia Simétrica"), background= "skyblue4",fg="white")
symmetric_difference_button.grid(row=9, column=0, padx=0, pady=1)

result_label = tk.Label(root, text="Resultado:",bg="dark olive green",fg="white")
result_label.grid(row=11, column=1,columnspan=3,pady=10)


reset_button = tk.Button(root, text="Reiniciar", command=reset_entries,bg="gray",fg="white")
reset_button.grid(row=11,column=0,pady=10)



root.mainloop()

#Integrantes:
#Luis Gerardo Cacho Nafarrate
#MIGUEL ANGEL UREÑA RODRIGUEZ
#ZYANYA CITLALLI ORTEGA LUA