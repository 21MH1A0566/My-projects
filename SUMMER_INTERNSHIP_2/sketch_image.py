# from tkinter import Label
# import cv2
# from styles import configure_styles
# from menu_bar import create_menubar
# from image_operations import load_image, resize_image, apply_sketch_effect, display_image
# from file_operations import open_file_dialog, save_sketch_image
# from gui_elements import create_main_frame, create_intensity_scale

# class SketchImage:
#     def __init__(self, root):
#         self.window = root
#         self.window.geometry("1920x1080")
#         self.window.title('Sketch Creator')
#         self.window.resizable(width=True, height=True)

#         self.style = configure_styles()
#         self.Image_Path = ''
#         self.SketchImg = ''
#         self.original_image_size = None

#         create_menubar(self.window, self.open_image, self.create_sketch, self.save_image, self.exit_app)
#         self.frame = create_main_frame(self.window)
#         self.intensity = create_intensity_scale(self.window)

#     def open_image(self):
#         self.clear_screen()
#         self.Image_Path = open_file_dialog()
#         if self.Image_Path:
#             self.display_image(self.Image_Path)

#     def display_image(self, img_path):
#         image = load_image(img_path)
#         self.original_image_size = image.size
#         window_height = self.window.winfo_height() - 200
#         resized_image = resize_image(image, self.window.winfo_width(), window_height)
#         display_image(self.window, self.frame, resized_image)

#     def create_sketch(self):
#         if not self.Image_Path:
#             return
#         self.SketchImg = apply_sketch_effect(self.Image_Path, self.intensity.get())
#         output_img = cv2.resize(self.SketchImg, (self.original_image_size[0], self.original_image_size[1]))
#         cv2.imshow("Sketch", output_img)
#         cv2.waitKey(0)
#         cv2.destroyAllWindows()

#     def save_image(self):
#         if self.SketchImg is not None:
#             save_sketch_image(self.SketchImg, self.Image_Path)

#     def clear_screen(self):
#         for widget in self.frame.winfo_children():
#             widget.destroy()

#     def exit_app(self):
#         self.window.destroy()














# from tkinter import Label
# import cv2
# from styles import configure_styles
# from menu_bar import create_menubar
# from image_operations import load_image, resize_image, apply_sketch_effect, display_image
# from file_operations import open_file_dialog, save_sketch_image
# from gui_elements import create_main_frame, create_intensity_scale

# class SketchImage:
#     def __init__(self, root):
#         self.window = root
#         self.window.geometry("1920x1080")
#         self.window.title('Sketch Creator')
#         self.window.resizable(width=True, height=True)

#         self.style = configure_styles()
#         self.Image_Path = ''
#         self.SketchImg = ''
#         self.original_image_size = None

#         # Set up the menu bar, frame, and intensity scale
#         create_menubar(self.window, self.open_image, self.create_sketch, self.save_image, self.exit_app)
#         self.frame = create_main_frame(self.window)
#         self.intensity = create_intensity_scale(self.window)

#     def open_image(self):
#         # Clear previous image and load a new one
#         self.clear_screen()
#         self.Image_Path = open_file_dialog()
#         if self.Image_Path:
#             self.display_image(self.Image_Path)

#     def display_image(self, img_path):
#         # Load and display the original image
#         image = load_image(img_path)
#         self.original_image_size = image.size
#         window_height = self.window.winfo_height() - 200
#         resized_image = resize_image(image, self.window.winfo_width(), window_height)
#         display_image(self.window, self.frame, resized_image)

#     def create_sketch(self):
#         if not self.Image_Path:
#             return
#         # Apply the sketch effect using the original image size
#         self.SketchImg = apply_sketch_effect(self.Image_Path, self.intensity.get())
#         sketch_img_resized = cv2.resize(self.SketchImg, self.original_image_size)  # Match height to the original

#         # Display the sketch with the original image's height
#         cv2.imshow("Sketch", sketch_img_resized)
#         cv2.waitKey(0)
#         cv2.destroyAllWindows()

#     def save_image(self):
#         # Save the sketch if it exists
#         if self.SketchImg is not None:
#             save_sketch_image(self.SketchImg, self.Image_Path)

#     def clear_screen(self):
#         # Clear all widgets from the frame
#         for widget in self.frame.winfo_children():
#             widget.destroy()

#     def exit_app(self):
#         # Exit the application
#         self.window.destroy()





















from tkinter import Label
from tkinter import ttk,Tk
import cv2
from tkinter import messagebox
from styles import configure_styles
from menu_bar import create_menubar
from image_operations import load_image, resize_image, apply_sketch_effect, display_image
from file_operations import open_file_dialog, save_sketch_image
from gui_elements import create_main_frame, create_intensity_scale
from file_operations import show_custom_message

class SketchImage:
    def __init__(self, root):
        self.window = root
        self.window.geometry("1920x1080")
        self.window.title('Sketch Creator') 
        self.window.resizable(width=True, height=True)

        self.style =configure_styles()
        self.Image_Path = ''
        self.SketchImg = ''
        self.original_image_size = None

        create_menubar(self.window, self.open_image, self.create_sketch, self.save_image, self.exit_app)
        self.frame = create_main_frame(self.window )
        self.intensity = create_intensity_scale(self.window)
        self.intensity.pack(pady=(20, 100)) 
         


    def open_image(self):
        self.clear_screen()
        self.Image_Path = open_file_dialog()
        if self.Image_Path:
            self.display_image(self.Image_Path)

    def display_image(self, img_path):
        image = load_image(img_path)
        self.original_image_size = image.size
        window_height = self.window.winfo_height() - 300
        resized_image = resize_image(image, self.window.winfo_width(), window_height)
        display_image(self.window, self.frame, resized_image,apply_action=self.create_sketch)

    def create_sketch(self):
        if not self.Image_Path:
            show_custom_message("Warning",  "Please select a file.", self.window)


            # messagebox.showwarning("Warning", "Please select a file.")
            #return 

        else:
            # Apply the sketch effect to the image
            sketch_img = apply_sketch_effect(self.Image_Path, self.intensity.get()) 
            
            # Resize the sketch to match the original image's size
            #output_img = cv2.resize(sketch_img, self.original_image_size) 
            # Resize the output image to fit the window height while maintaining aspect ratio
            window_height = self.window.winfo_height() - 200
            aspect_ratio = self.original_image_size[0] / self.original_image_size[1]
            new_height = min(self.original_image_size[1], window_height)
            new_width = int(new_height * aspect_ratio)  # Auto width based on new height

            # Resize the sketch image to new dimensions
            output_img = cv2.resize(sketch_img, (new_width, new_height))
            
            # Show the resized sketch
            cv2.imshow("Sketch", output_img)
            cv2.waitKey(0)
            cv2.destroyAllWindows()
            
            # Update the sketch image for saving purposes
            self.SketchImg = output_img

    def save_image(self):
        if self.SketchImg is not None:
            save_sketch_image(self.SketchImg, self.Image_Path,self.window)
        else:
            messagebox.showwarning("Warning", "No Sketch to save.")

    def clear_screen(self):
        for widget in self.frame.winfo_children():
            widget.destroy()

    def exit_app(self):
        self.window.destroy()






 