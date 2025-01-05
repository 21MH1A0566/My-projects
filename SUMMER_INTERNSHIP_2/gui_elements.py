from tkinter import Scale, Frame, BOTH 
def create_main_frame(window): 
    frame = Frame(window, padx=20, pady=20 ) 

    frame.pack(expand=True, fill=BOTH)
    return frame

def create_intensity_scale(window):
    intensity = Scale(window, from_=5, to=155, resolution=2, orient='horizontal', length=300, border=10)
    intensity.set(37)
    intensity.place(relx=0.5, rely=0.9, anchor='center') 
    return intensity
