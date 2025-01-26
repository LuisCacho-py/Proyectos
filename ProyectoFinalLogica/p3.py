import tkinter as tk
import subprocess

def volver():
    ventana.destroy()  # Cerrar la ventana del menú
    subprocess.Popen(["python", "menu2.py"])  # Ajusta la ruta del ejecutable

def calcular_sucesion(limite_inferior, limite_superior, formula):
    resultados = []
    
    if limite_inferior > limite_superior:
        limite_inferior, limite_superior = limite_superior, limite_inferior
    
    for k in range(limite_inferior, limite_superior + 1):
        try:
            resultado = eval(formula)
            resultados.append(resultado)
        except ZeroDivisionError:
            resultados.append(0)
        except Exception as e:
            resultados.append(None)  # Manejo básico de excepciones, podrías mejorar esto
        
    return resultados

def generar_calculo():
    try:
        limite_inf = int(entry_limite_inf.get())
        limite_sup = int(entry_limite_sup.get())
    except ValueError:
        texto_resultados.config(state=tk.NORMAL)
        texto_resultados.insert(tk.END, "Por favor, ingrese valores numéricos para los límites.")
        texto_resultados.config(state=tk.DISABLED)
        return
    
    if limite_inf > limite_sup:
        limite_inf, limite_sup = limite_sup, limite_inf
    
    formula = entry_formula.get()
    resultados = calcular_sucesion(limite_inf, limite_sup, formula)
    texto_resultados.config(state=tk.NORMAL)
    texto_resultados.delete('1.0', tk.END)
    cont=1
    suma=0
    multi=1
    for resultado in resultados:
        texto_resultados.insert(tk.END,f"Resultado({cont}): " + str(resultado) + '\n')
        cont += 1
        suma += resultado
        multi *= resultado
    texto_resultados.insert(tk.END, f"La suma de la sucesion es: {suma:.10f}\n")
    texto_resultados.insert(tk.END,f"La multiplicacion de la sucesion es: {multi}\n")
    texto_resultados.config(state=tk.DISABLED)

# Creación de la ventana y otros elementos de la interfaz
ventana = tk.Tk()
ventana.title("Calculadora de Sucesiones")
ventana.config(bg="grey71")

# Elementos de la interfaz
tk.Button(ventana, text="Volver al menú", background="sea green", fg="white", command=volver).grid(row=0,columnspan=1, pady=5, padx=10)
tk.Label(ventana, text="Límite Inferior:", background="light grey").grid(row=1, column=0, padx=5, pady=5)
entry_limite_inf = tk.Entry(ventana)
entry_limite_inf.grid(row=1, column=1, padx=0, pady=5)
tk.Label(ventana, text="Límite Superior:", background="light grey").grid(row=2, column=0, padx=5, pady=5)
entry_limite_sup = tk.Entry(ventana)
entry_limite_sup.grid(row=2, column=1, padx=5, pady=5)
tk.Label(ventana, text="Fórmula (k):", background="light grey").grid(row=3, column=0, padx=5, pady=5)
entry_formula = tk.Entry(ventana)
entry_formula.grid(row=3, column=1, padx=5, pady=5)
boton_calcular = tk.Button(ventana, text="Generar Cálculo", command=generar_calculo, background="skyblue4")
boton_calcular.grid(row=4, column=0, columnspan=2, padx=5, pady=5)
texto_resultados = tk.Text(ventana, height=20, width=40, bg="light grey")
texto_resultados.grid(row=5, column=0, columnspan=2, padx=5, pady=5)
texto_resultados.config(state=tk.DISABLED)

ventana.mainloop()
