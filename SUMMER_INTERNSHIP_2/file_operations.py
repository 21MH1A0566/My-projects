# import pathlib
# from tkinter import filedialog
# import pyautogui
# import cv2
# import os
# from tkinter import messagebox
# def open_file_dialog():
#     return filedialog.askopenfilename(title="Select an Image", filetypes=(("Image files", "*.jpg *.jpeg *.png"),))

# def save_sketch_image(sketch_img, original_path):
#     # if len(sketch_img)==0:
#     #     messagebox.showwarning("Warning","No Sketch to save.")
#     # else:
#     #     filename = pyautogui.prompt("Enter the filename to be saved")
#     #     if filename:
#     #         filename += pathlib.Path(original_path).suffix
#     #         cv2.imwrite(filename, sketch_img)
#     #         messagebox.showinfo("Success","Image saved successfully")



#     if len(sketch_img) == 0:
#         messagebox.showwarning("Warning", "No Sketch to save.")
#     else:
#         # Prompt user to enter a filename
#         filename = pyautogui.prompt("Enter the filename to be saved")
#         if filename:
#             # Add the original file extension to the new filename
#             filename += pathlib.Path(original_path).suffix
            
#             # Check if file already exists in the directory
#             if os.path.exists(filename):
#                 # Prompt user with a message box if file already exists
#                 overwrite = messagebox.askyesno("File Exists", f"The file '{filename}' already exists. Do you want to overwrite it?")
                
#                 if not overwrite:
#                     messagebox.showinfo("Save Cancelled", "File not saved.")
#                     return
            
#             # Save the image if file does not exist or user confirmed overwrite
#             cv2.imwrite(filename, sketch_img)
#             messagebox.showinfo("Success", "Image saved successfully")















# import pathlib
# from tkinter import filedialog, Toplevel, Label, Button
# import pyautogui
# import cv2
# import os
# from tkinter import messagebox, Tk

# def show_custom_message(title, message, width=500, height=400):
#     # Create a custom Toplevel window
#     custom_msg = Toplevel()
#     custom_msg.title(title)
#     custom_msg.geometry(f"{width}x{height}")

     

    
#     # Configure layout and content
#     label = Label(custom_msg, text=message, wraplength=450, font=("Arial", 14))
#     label.pack(pady=20)
    
#     ok_button = Button(custom_msg, text="OK", command=custom_msg.destroy, font=("Arial", 12))
#     ok_button.pack(pady=10)
    
#     # Prevent interaction with other windows until this is closed
#     custom_msg.transient()  # Stay on top
#     custom_msg.grab_set()   # Modal window
#     custom_msg.mainloop()

# def open_file_dialog():
#     return filedialog.askopenfilename(title="Select an Image", filetypes=(("Image files", "*.jpg *.jpeg *.png"),))

# def save_sketch_image(sketch_img, original_path):
#     if len(sketch_img) == 0:
#         show_custom_message("Warning", "No Sketch to save.")
#     else:
#         # Prompt user to enter a filename
#         filename = pyautogui.prompt("Enter the filename to be saved")
#         if filename:
#             # Add the original file extension to the new filename
#             filename += pathlib.Path(original_path).suffix
            
#             # Check if file already exists in the directory
#             if os.path.exists(filename):
#                 # Prompt user with a custom message box if file already exists
#                 overwrite = messagebox.askyesno("File Exists", f"The file '{filename}' already exists. Do you want to overwrite it?")
                
#                 if not overwrite:
#                     show_custom_message("Save Cancelled", "File not saved.")
#                     return
            
#             # Save the image if file does not exist or user confirmed overwrite
#             cv2.imwrite(filename, sketch_img)
#             show_custom_message("Success", "Image saved successfully")












import pathlib
from tkinter import filedialog, Toplevel, Label, Button, messagebox, Tk,Frame,LEFT
import pyautogui
import cv2
import os

def show_custom_message(title, message, parent,width=500, height=250):
    
    # Create a custom Toplevel window
    custom_msg = Toplevel(parent)
    custom_msg.title(title)
    custom_msg.geometry(f"{width}x{height}")

    # Center the message box on the parent window
    parent_width = parent.winfo_width()
    parent_height = parent.winfo_height()
    x = parent.winfo_x() + (parent_width // 2) - (width // 2)
    y = parent.winfo_y() + (parent_height // 2) - (height // 2)
    custom_msg.geometry(f"{width}x{height}+{x}+{y}")

    # Configure layout and content
    label = Label(custom_msg, text=message, wraplength=450, font=("Arial", 14))
    label.pack(pady=20)
    
    # ok_button = Button(custom_msg, text="OK", command=custom_msg.destroy, font=("Arial", 12))
    # ok_button.pack(pady=10)

    # close_button = Button(custom_msg, text="CLOSE", command=custom_msg.destroy, font=("Arial", 12))
    # close_button.pack(pady=10)


     # Create a frame to hold the buttons
    button_frame = Frame(custom_msg)
    button_frame.pack(pady=10)

    # Create OK button
    ok_button = Button(button_frame, text="OK", command=custom_msg.destroy, font=("Arial", 12))
    ok_button.pack(side=LEFT, padx=(0, 20))  # Add space to the right of OK button

    # Create CLOSE button
    close_button = Button(button_frame, text="CLOSE", command=custom_msg.destroy, font=("Arial", 12))
    close_button.pack(side=LEFT)

    
    # Prevent interaction with other windows until this is closed
    custom_msg.transient(parent)  # Stay on top of the parent window
    custom_msg.grab_set()          # Make it modal
    custom_msg.focus_set()         # Focus on this window
    custom_msg.wait_window()       # Wait for it to close

def open_file_dialog():
    return filedialog.askopenfilename(title="Select an Image", filetypes=(("Image files", "*.jpg *.jpeg *.png"),))

def save_sketch_image(sketch_img, original_path, parent):
    if len(sketch_img) == 0:
        show_custom_message("Warning", "No Sketch to save.", parent)
    else:
        # Prompt user to enter a filename
        filename = pyautogui.prompt("Enter the filename to be saved")
         
        if filename:
            # Add the original file extension to the new filename
            filename += pathlib.Path(original_path).suffix
            
            # Check if file already exists in the directory
            if os.path.exists(filename):
                # Prompt user with a custom message box if file already exists
                overwrite = messagebox.askyesno("File Exists", f"The file '{filename}' already exists. Do you want to overwrite it?")
                
                if not overwrite:
                    show_custom_message("Save Cancelled", "File not saved.", parent)
                    return
            
            # Save the image if file does not exist or user confirmed overwrite
            cv2.imwrite(filename, sketch_img)
            show_custom_message("Success", "Image saved successfully", parent)

        else:
            show_custom_message("Failed", "Please enter a valid File Name", parent)










 
