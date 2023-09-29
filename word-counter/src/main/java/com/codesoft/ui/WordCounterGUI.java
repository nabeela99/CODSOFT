package com.codesoft.ui;

import com.codesoft.util.Constants;
import com.codesoft.util.Counter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static com.codesoft.util.Constants.FILE_ICON;
import static com.codesoft.util.Constants.TEXT_ICON;


public class WordCounterGUI extends JPanel implements ActionListener {

    private JButton openButton;
    private JButton fileWordCountButton;
    private JButton textWordCountButton;
    private JFileChooser fileChooser;

    private JTextArea fileTextArea;
    private JTextArea textInputTextArea;
    private JTextArea textOutput;
    private JTextArea fileOutput;

    public WordCounterGUI() {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon fileIcon = createImageIcon(FILE_ICON);
        ImageIcon textIcon = createImageIcon(TEXT_ICON);

        JComponent textPanel = makeTextPanel(Constants.TEXT);
        tabbedPane.addTab("Enter Text", textIcon, textPanel,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        String FILE = "file";
        JComponent filePanel = makeTextPanel(FILE);
        tabbedPane.addTab("Upload Text File", fileIcon, filePanel,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        //Add the tabbed pane to this panel.
        add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setPreferredSize(new Dimension(800, 600));
    }

    protected JComponent makeTextPanel(String tabName) {
        JPanel panel = new JPanel(false);
        if (Constants.TEXT.equals(tabName)) {
            textInputTextArea = new JTextArea(10, 60);
            this.textInputTextArea.setEditable(true);
            this.textInputTextArea.setVisible(true);
            this.textInputTextArea.setLineWrap(true);
            JScrollPane inputScroll = new JScrollPane (this.textInputTextArea);
            inputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            inputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel.add(inputScroll);

            this.textWordCountButton = new JButton("Count Words");
            this.textWordCountButton.setSize(new Dimension(5,5));
            this.textWordCountButton.addActionListener(this);
            panel.add(textWordCountButton);

            this.textOutput = new JTextArea(10, 60);
            this.textOutput.setLineWrap(true);
            this.textOutput.setEditable(false);
            this.textOutput.setVisible(true);
            JScrollPane outputScroll = new JScrollPane (this.textOutput);
            outputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            outputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel.add(outputScroll);

        } else {
            this.fileChooser = new JFileChooser();
            this.openButton = new JButton("Open a File...");
            this.openButton.setSize(new Dimension(5,5));
            this.openButton.addActionListener(this);
            panel.add(openButton);

            this.fileTextArea = new JTextArea(10, 60);
            this.fileTextArea.setEditable(false);
            this.fileTextArea.setVisible(true);
            this.fileTextArea.setLineWrap(true);
            JScrollPane inputScroll = new JScrollPane (this.fileTextArea);
            inputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            inputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel.add(inputScroll);

            this.fileWordCountButton = new JButton("Count Words");
            this.fileWordCountButton.setSize(new Dimension(5,5));
            this.fileWordCountButton.addActionListener(this);
            panel.add(fileWordCountButton);

            this.fileOutput = new JTextArea(10, 60);
            this.fileOutput.setEditable(false);
            this.fileOutput.setVisible(true);
            this.fileOutput.setLineWrap(true);

            JScrollPane outputScroll = new JScrollPane (this.fileOutput);
            outputScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            outputScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            panel.add(outputScroll);
        }
        return panel;
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        try {
            java.net.URL imgURL = Path.of(path).toUri().toURL();
            return new ImageIcon(imgURL);
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("WordCounterUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new WordCounterGUI(), BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.openButton) {
            int returnVal = this.fileChooser.showOpenDialog(WordCounterGUI.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = this.fileChooser.getSelectedFile();
                try {
                    this.fileTextArea.setText("");
                    this.fileTextArea.append(Files.readString(Path.of(file.getPath())));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                this.fileTextArea.append("Open command cancelled by user." + Constants.NEWLINE);
            }
        }

        if (e.getSource() == this.textWordCountButton) {
            var textData = this.textInputTextArea.getText();
            if (!this.textOutput.getText().isEmpty()) {
                this.textOutput.append("\n\n");
            }

            this.textOutput.append(String.format("Output at: %s\n",
                    LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                            .localizedBy(Locale.UK))));
            this.textOutput.append(Counter.getCountedWordsForDisplay(textData));
        }

        if (e.getSource() == this.fileWordCountButton) {
            var textData = this.fileTextArea.getText();
            if (!this.fileOutput.getText().isEmpty()) {
                this.fileOutput.append("\n\n");
            }
            this.fileOutput.append(String.format("Output at: %s\n",
                    LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                            .localizedBy(Locale.UK))));
            this.fileOutput.append(Counter.getCountedWordsForDisplay(textData));
        }
    }
}
