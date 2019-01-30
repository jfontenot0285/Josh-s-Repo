import tkinter
import tkinter.messagebox

class WelcomeGUI:
    def __init__(self):
        self.main_window = tkinter.Tk()
        self.main_window.title('DiceMan')
        self.top_frame = tkinter.Frame(self.main_window)
        self.mid_frame = tkinter.Frame(self.main_window)
        self.bot_frame = tkinter.Frame(self.main_window)

        self.top_label = tkinter.Label(self.top_frame,\
                                       text='Welcome to Dice Man! Players, identify yourselves:')
        self.top_label.pack()

        self.p1name_label = tkinter.Label(self.mid_frame,text='Player 1:')
        self.p1name_entry = tkinter.Entry(self.mid_frame,width=10)
        self.p2name_label = tkinter.Label(self.mid_frame,text='Player 2:')
        self.p2name_entry = tkinter.Entry(self.mid_frame,width=10)

        self.p1name_label.pack()
        self.p1name_entry.pack()
        self.p2name_label.pack()
        self.p2name_entry.pack()

        self.confirm_button = tkinter.Button(self.bot_frame, \
                                            text='Confirm',\
                                            command=self.vs)
        self.confirm_button.pack()

        self.top_frame.pack()
        self.mid_frame.pack()
        self.bot_frame.pack()

        tkinter.mainloop()
    
    def vs(self):
        p1 = self.p1name_entry.get()
        p2 = self.p2name_entry.get()
        tkinter.messagebox.showinfo('Get ready', p1 + ' versus ' + p2 + '!')
        self.main_window.destroy()
