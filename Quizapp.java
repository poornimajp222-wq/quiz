public class Main {
    public static void main(String[] args) {
        new StudentForm();
    }
}
public class Question {

    String question;
    String[] options;
    int answer;

    public Question(String question, String[] options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentForm extends JFrame implements ActionListener {

    JTextField nameField, branchField, semField, usnField;
    JButton startButton;

    public StudentForm() {

        setTitle("Technical Quiz Application");
        setSize(600, 500);
        setLayout(null);
        getContentPane().setBackground(new Color(30, 30, 60));

        JLabel heading = new JLabel("<html><h1 style='color:white;'>TECHNICAL QUIZ</h1></html>");
        heading.setBounds(180, 20, 300, 50);
        add(heading);

        JLabel name = new JLabel("Name:");
        name.setBounds(100, 100, 100, 30);
        name.setForeground(Color.WHITE);
        add(name);

        nameField = new JTextField();
        nameField.setBounds(220, 100, 200, 30);
        add(nameField);

        JLabel branch = new JLabel("Branch:");
        branch.setBounds(100, 150, 100, 30);
        branch.setForeground(Color.WHITE);
        add(branch);

        branchField = new JTextField();
        branchField.setBounds(220, 150, 200, 30);
        add(branchField);

        JLabel sem = new JLabel("Semester:");
        sem.setBounds(100, 200, 100, 30);
        sem.setForeground(Color.WHITE);
        add(sem);

        semField = new JTextField();
        semField.setBounds(220, 200, 200, 30);
        add(semField);

        JLabel usn = new JLabel("USN:");
        usn.setBounds(100, 250, 100, 30);
        usn.setForeground(Color.WHITE);
        add(usn);

        usnField = new JTextField();
        usnField.setBounds(220, 250, 200, 30);
        add(usnField);

        startButton = new JButton("Start Quiz");
        startButton.setBounds(220, 330, 150, 40);
        startButton.setBackground(new Color(0, 150, 255));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(this);
        add(startButton);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {

        String name = nameField.getText();

        if(name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter Name");
        } else {
            dispose();
            new QuizFrame(name);
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizFrame extends JFrame implements ActionListener {

    JLabel questionLabel, timerLabel;
    JRadioButton op1, op2, op3, op4;
    ButtonGroup bg;
    JButton nextButton;

    Question[] questions;

    int index = 0;
    int score = 0;
    int time = 15;

    Timer timer;

    String studentName;

    public QuizFrame(String name) {

        studentName = name;

        setTitle("Quiz");
        setSize(800, 500);
        setLayout(null);

        getContentPane().setBackground(new Color(20, 20, 40));

        timerLabel = new JLabel("Time Left: 15");
        timerLabel.setBounds(600, 20, 150, 30);
        timerLabel.setForeground(Color.YELLOW);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(timerLabel);

        questionLabel = new JLabel();
        questionLabel.setBounds(50, 50, 700, 50);
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(questionLabel);

        op1 = new JRadioButton();
        op2 = new JRadioButton();
        op3 = new JRadioButton();
        op4 = new JRadioButton();

        JRadioButton[] options = {op1, op2, op3, op4};

        int y = 120;

        for(JRadioButton op : options) {
            op.setBounds(100, y, 500, 40);
            op.setBackground(new Color(20, 20, 40));
            op.setForeground(Color.WHITE);
            op.setFont(new Font("Arial", Font.PLAIN, 16));
            add(op);
            y += 50;
        }

        bg = new ButtonGroup();
        bg.add(op1);
        bg.add(op2);
        bg.add(op3);
        bg.add(op4);

        nextButton = new JButton("Next");
        nextButton.setBounds(300, 350, 150, 40);
        nextButton.setBackground(new Color(0, 180, 120));
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        add(nextButton);

        loadQuestions();
        displayQuestion();

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                time--;
                timerLabel.setText("Time Left: " + time);

                if(time == 0) {
                    checkAnswer();
                    nextQuestion();
                }
            }
        });

        timer.start();

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    void loadQuestions() {

        questions = new Question[] {

            new Question("Which language is platform independent?",
                    new String[]{"C", "C++", "Java", "Python"}, 2),

            new Question("Which data structure uses FIFO?",
                    new String[]{"Stack", "Queue", "Array", "Tree"}, 1),

            new Question("Which keyword is used for inheritance in Java?",
                    new String[]{"implement", "inherits", "extends", "super"}, 2),

            new Question("HTML stands for?",
                    new String[]{"Hyper Text Markup Language", "HighText Machine Language", "Hyperlinks", "None"}, 0),

            new Question("Which protocol is used for web?",
                    new String[]{"FTP", "HTTP", "SMTP", "TCP"}, 1),

            new Question("Which company developed Java?",
                    new String[]{"Google", "Sun Microsystems", "Microsoft", "Apple"}, 1),

            new Question("Which is not an OOP concept?",
                    new String[]{"Inheritance", "Polymorphism", "Compilation", "Encapsulation"}, 2),

            new Question("Which database language is used for queries?",
                    new String[]{"C", "SQL", "HTML", "Python"}, 1),

            new Question("Which sorting is fastest on average?",
                    new String[]{"Bubble", "Selection", "Quick Sort", "Insertion"}, 2),

            new Question("Which symbol is used for comments in Java?",
                    new String[]{"//", "#", "<!--", "**"}, 0)
        };
    }

    void displayQuestion() {

        bg.clearSelection();

        Question q = questions[index];

        questionLabel.setText((index + 1) + ". " + q.question);

        op1.setText(q.options[0]);
        op2.setText(q.options[1]);
        op3.setText(q.options[2]);
        op4.setText(q.options[3]);

        time = 15;
    }

    void checkAnswer() {

        int selected = -1;

        if(op1.isSelected()) selected = 0;
        if(op2.isSelected()) selected = 1;
        if(op3.isSelected()) selected = 2;
        if(op4.isSelected()) selected = 3;

        if(selected == questions[index].answer) {
            score++;
        }
    }

    void nextQuestion() {

        checkAnswer();

        index++;

        if(index >= questions.length) {
            timer.stop();
            dispose();
            new ResultFrame(studentName, score, questions.length);
        } else {
            displayQuestion();
        }
    }

    public void actionPerformed(ActionEvent e) {
        nextQuestion();
    }
}
import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {

    public ResultFrame(String name, int score, int total) {

        setTitle("Quiz Result");
        setSize(600, 400);
        setLayout(null);

        getContentPane().setBackground(new Color(25, 25, 50));

        JLabel heading = new JLabel("<html><h1 style='color:white;'>QUIZ RESULT</h1></html>");
        heading.setBounds(180, 30, 300, 50);
        add(heading);

        JLabel student = new JLabel("Student: " + name);
        student.setBounds(180, 100, 300, 30);
        student.setForeground(Color.WHITE);
        student.setFont(new Font("Arial", Font.BOLD, 20));
        add(student);

        JLabel result = new JLabel("Score: " + score + "/" + total);
        result.setBounds(180, 150, 300, 30);
        result.setForeground(Color.GREEN);
        result.setFont(new Font("Arial", Font.BOLD, 24));
        add(result);

        double percent = (score * 100.0) / total;

        JLabel percentage = new JLabel("Percentage: " + percent + "%");
        percentage.setBounds(180, 200, 300, 30);
        percentage.setForeground(Color.CYAN);
        percentage.setFont(new Font("Arial", Font.BOLD, 20));
        add(percentage);

        JLabel msg = new JLabel();

        if(percent >= 80)
            msg.setText("Excellent Performance!");
        else if(percent >= 50)
            msg.setText("Good Job!");
        else
            msg.setText("Keep Practicing!");

        msg.setBounds(180, 250, 300, 30);
        msg.setForeground(Color.ORANGE);
        msg.setFont(new Font("Arial", Font.BOLD, 20));
        add(msg);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}