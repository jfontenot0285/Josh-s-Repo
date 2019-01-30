import tkinter
import tkinter.messagebox

class TurnGUI:
    def __init__(self):
        self.root = tkinter.Tk()
        self.root.title('DiceMan')
        self.top_frame = tkinter.Frame(self.root)
        self.bot_frame = tkinter.Frame(self.root)
        
        self.radio_var = tkinter.IntVar()
        self.radio_var.set(1)

        self.rb1 = tkinter.Radiobutton(self.top_frame, \
                                        text='Warrior',variable=self.radio_var,\
                                        value=1)

        self.rb2 = tkinter.Radiobutton(self.top_frame, \
                                        text='Ranger',variable=self.radio_var,\
                                        value=2)

        self.rb3 = tkinter.Radiobutton(self.top_frame, \
                                        text='Tanker',variable=self.radio_var,\
                                        value=3)
        self.rb1.pack()
        self.rb2.pack()
        self.rb3.pack()

        self.confirm_button = tkinter.Button(self.bot_frame, text='Confirm',command=)
    
    def confirm(self):

