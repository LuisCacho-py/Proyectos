
print("Hola Bienvenido A Cacho's Store")
usuarios=[]
contrasena1=[]

print("Por favor registrate :) ")

usuario=input("Ingresa tu usuario: ")
usuarios.append(usuario)

mayus=False
num=False

while True:
    contrasena=input("Ingresa tu contraseña: Esta debe tener un numero, una mayuscula: ")


    for caract in contrasena:
        if caract.isupper():
            mayus=True
        elif caract.isdigit():
            num=True

    if mayus and num:
        break
    else:
        print("La contraseña no cumple con los requisitos :(")

contrasena1.append(contrasena)

estu=input("Eres estudiante? Si/No ")
if estu=="Si":
    des=True
else:
    des=False

print("Registro Exitoso :) \n")

usu1=len(usuarios)

print("Inicia Sesion ")

while True:
    newusu=input("Ingresa tu usuario: ")
    newcon=input("Intresa tu contraseña: ")
    encon=False
    for i in range (usu1):
        if contrasena1[i]==newcon and usuarios[i]==newusu:
            encon=True
            print(f"Bienvenido {newusu}")
            print()
            break
    if encon:
        break
    else:
        print("Tu usuario o contraseña no es valida :(")
        print()
        continue

zapatos = ["Zapatos Deportivos", "Zapatos Formales", "Zapatos Escolares"]
preciozapa = [1200, 1800, 800]

playeras = ["Camisa Oversize", "Camisa Polo", "Camisa Manga Larga"]
preciopla = [399, 650, 600]

pantalones = ["Pantalones Cargo", "Pantalones Jeans", "Pantalones Parachute"]
preciopanta = [799, 1000, 950]


sal=0
total=[]
produ=[]

elecion=0

while sal!=1:
    print("MENU\n 1.Zapatos \n 2.Playeras \n 3.Pantalones \n 4.Pagar \n 0.Cerrar Sesion")
    opc = int(input("Ingresa tu interes: (1,2,3,4,0) "))
    print()

###
    if opc==1:
        while True:
            print(f"Zapatos\n 1.{zapatos[0]} ${preciozapa[0]} \n 2.{zapatos[1]} ${preciozapa[1]}\n 3.{zapatos[2]} ${preciozapa[2]}\n 0.Salir")
            print()
            elecion=int(input("Ingresa tu eleccion: "))
            print()
            if elecion == 1:
                print(f"Tu eleccion es {elecion}")
                produ.append(zapatos[0])
                total.append(preciozapa[0])
                print(f"{zapatos[0]} ${preciozapa[0]}\n")
            elif elecion == 2:
                print(f"Tu eleccion es {elecion}")
                produ.append(zapatos[1])
                total.append(preciozapa[1])
                print(f"{zapatos[1]} ${preciozapa[1]}\n")
            elif elecion == 3:
                print(f"Tu eleccion es {elecion}")
                produ.append(zapatos[2])
                total.append(preciozapa[2])
                print(f"{zapatos[2]} ${preciozapa[2]}\n")
            elif (elecion) == 0:
                print()
                break
            else:
                continue
        else:
            print("Eleccion no valida\n")
            continue
###
    elif opc == 2:
        while True:
            print(f"Playeras\n 1.{playeras[0]} ${preciopla[0]} \n 2.{playeras[1]} ${preciopla[1]} \n 3.{playeras[2]} ${preciopla[2]}\n 0.Salir")
            print()
            elecion=int(input("Ingresa tu eleccion: "))
            print()
            if elecion == 1:
                print(f"Tu eleccion es {elecion}")
                total.append(preciopla[0])
                produ.append(playeras[0])
                print(f"{playeras[0]} ${preciopla[0]}\n")
            elif elecion == 2:
                print(f"Tu eleccion es {elecion}")
                total.append(preciopla[1])
                produ.append(playeras[1])
                print(f"{playeras[1]} ${preciopla[1]}\n")
            elif elecion == 3:
                print(f"Tu eleccion es {elecion}")
                total.append(preciopla[2])
                produ.append(playeras[2])
                print(f"{playeras[2]} ${preciopla[2]}\n")
            elif elecion == 0:
                print()
                break
            else:
                print("Eleccion no valida\n")
                continue
        else:
            print("Eleccion no valida\n")
            continue
###
    elif opc==3:
        while True:
            print(f"Pantalones\n 1.{pantalones[0]} ${preciopanta[0]}\n 2.{pantalones[1]} ${preciopanta[1]}\n 3.{pantalones[2]} ${preciopanta[2]}\n 0.Salir")
            print()
            elecion=int(input("Ingresa tu eleccion: "))
            print()
            if elecion == 1:
                print(f"Tu eleccion es {elecion}")
                total.append(preciopanta[0])
                produ.append(pantalones[0])
                print(f"{pantalones[0]} ${preciopanta[0]}\n")
            elif elecion == 2:
                print(f"Tu eleccion es {elecion}")
                total.append(preciopanta[1])
                produ.append(pantalones[1])
                print(f"{pantalones[1]} ${preciopanta[1]}\n")
            elif elecion == 3:
                total.append(preciopanta[2])
                produ.append(pantalones[2])
                print(f"{pantalones[2]} ${preciopanta[2]}\n")
            elif elecion == 0:
                print()
                break
            else:
                print("Eleccion no valida\n")
                continue
    elif opc==4:
        x=len(total)

        while True:
            for i in range(x):
                print(f"{produ[i]} ${total[i]}")
            if des==True:
               tot=sum(total)
               descu=tot*.75
               print(f"Como eres estudiante el total a pagar es: {descu}")
               break
            else:
                print("Total a pagar $",sum(total))
                break
        print(f"Pagar\n 1.Pagar \n 0.Salir")
        print()
        elecion = int(input("Ingresa tu eleccion: "))
        print()
        while True:
            if elecion == 1:
                print("Gracias por tu compra")
                sal=1
                break
            elif elecion == 0:
                break
    elif opc==0:
        print("Gracias por visitar nuestra tienda")
        break











