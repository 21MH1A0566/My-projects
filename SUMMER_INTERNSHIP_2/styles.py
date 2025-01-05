# from tkinter import ttk,Tk
# from tkinter import Label
# def configure_styles():
#     style = ttk.Style()

#     # style.configure('TFrame', background='pink')
#     # #style.configure('TLabel', background='lightgrey', font=('Arial', 16))
#     # style.configure('Custom.TLabel', background='navy', foreground='white', font=('Arial', 16, 'bold'))
#     # style.configure('TMenu', background='white', font=('Arial', 12))
#     # style.configure('TScale', background='lightgrey')
#     # style.configure('TButton', background='blue', foreground='white', font=('Arial', 12))








#       # Configure bright colors for the frame background
#     style.configure('TFrame', background='#ffeb99')  # Light yellow
    
#     # Custom label style with bright background and foreground
#     style.configure('Bright.TLabel', background='#ff9900', foreground='white', font=('Arial', 16, 'bold'))  # Bright orange with white text
    
#     # Configure bright button style
#     style.configure('Bright.TButton', background='#66ccff', foreground='black', font=('Arial', 12, 'bold'))  # Bright blue
    
#     # Bright scale background
#     style.configure('TScale', background='#ffeb99')
    
#     # Customize menu font and color
#     style.configure('TMenu', background='#66ccff', font=('Arial', 12))
    
#     return style

from tkinter import ttk

def configure_styles():
    style = ttk.Style()
    
    # Frame style with light yellow background
    style.configure('TFrame', background='#ffeb99')  # Light yellow

    # Label style with bright orange background, white bold text
    style.configure('Bright.TLabel', background='#ff9900', foreground='white', font=('Arial', 16, 'bold'))

    # Button style with bright blue background, black text
    style.configure('Bright.TButton', background='#66ccff', foreground='black', font=('Arial', 12, 'bold'))

    # Scale style with consistent yellow background
    style.configure('TScale', background='#ffeb99')

    # Menu style with bright blue background and Arial font
    style.configure('TMenu', background='#66ccff', font=('Arial', 12))
 

    return style
