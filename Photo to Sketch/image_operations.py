import cv2
from PIL import Image, ImageTk
from tkinter import Label, Button
from tkinter import ttk,Tk 

def load_image(path):
    return Image.open(path)

def resize_image(image, width, height):
    aspect_ratio = image.width / image.height
    new_height = min(image.height, height)
    new_width = int(new_height * aspect_ratio)
    return image.resize((new_width, new_height))

def apply_sketch_effect(img_path, intensity):
    img = cv2.imread(img_path)
    gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    inverted_img = cv2.bitwise_not(gray_img)
    smooth_img = cv2.medianBlur(inverted_img, ksize=intensity)
    inverted_smooth_img = cv2.bitwise_not(smooth_img)
    return cv2.divide(gray_img, inverted_smooth_img, scale=250)

def display_image(window, frame, img,apply_action):
    img_tk = ImageTk.PhotoImage(img)
    label = Label(frame, image=img_tk)
    label.image = img_tk
    label.pack()

    # # Create and display the "Apply" button below the image
    # apply_button = Button(frame, text="Apply", command=lambda: print("Apply button clicked!"))
    # apply_button.config(bg="lightblue", fg="black", font=("Arial", 12, "bold"), padx=10, pady=5)
    # apply_button.pack()


    label.pack(pady=10)

    # Add the 'Apply' button below the image
    apply_button = ttk.Button(frame, text="Apply", command=apply_action,style='Bright.TButton')
    apply_button.pack(padx=5,pady=10)
 