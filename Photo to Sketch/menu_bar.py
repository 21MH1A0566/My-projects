from tkinter import Menu

def create_menubar(window, open_command, sketch_command, save_command, exit_command):
    menubar = Menu(window)
    
    # Open Menu
    open_menu = Menu(menubar, tearoff=0)
    menubar.add_cascade(label='Open', menu=open_menu)
    open_menu.add_command(label='Open Image', command=open_command)

    # Sketch Menu
    sketch_menu = Menu(menubar, tearoff=0)
    menubar.add_cascade(label='Sketch', menu=sketch_menu)
    sketch_menu.add_command(label='Create Sketch', command=sketch_command)

    # Save Menu
    save_menu = Menu(menubar, tearoff=0)
    menubar.add_cascade(label='Save', menu=save_menu)
    save_menu.add_command(label='Save Image', command=save_command)

    # Exit Menu
    exit_menu = Menu(menubar, tearoff=0)
    menubar.add_cascade(label='Exit', menu=exit_menu)
    exit_menu.add_command(label='Exit', command=exit_command)

    window.config(menu=menubar)
    return menubar
