package pokerapp;

import java.awt.Color;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pokerapp.window.Frame;

/**
 * The main class of the application.
 */
public class PokerApp extends SingleFrameApplication {

    /**
     * Main method launching the application.
     */
    private static final Logger logger = Logger.getLogger(PokerApp.class.getName());

    private Frame frame;

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of PokerApp
     */
    public static PokerApp getApplication() {
        return Application.getInstance(PokerApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
                /*
         * Set the Nimbus look and feel
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        /*
         * Create and display the form
         */
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                PokerApp poker = new PokerApp();
                poker.setFrame(new Frame());
                poker.getFrame().setSize(800, 600);
                poker.getFrame().setBackground(Color.green);
                poker.getFrame().setVisible(true);
            }
        });
    }

    @Override
    protected void startup() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
