package com.nova.engine;


import com.nova.game.Game;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private Game game;
    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];

    private final int NUM_BUTTONS = 5;
    private boolean[] buttons = new boolean[NUM_BUTTONS];
    private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private int mouseX, mouseY;
    private int scroll;


    public Input(Game game) {
        this.game = game;

        mouseX = 0;
        mouseY = 0;
        scroll = 0;
        game.getWindow().getCanvas().addKeyListener(this);
        game.getWindow().getCanvas().addMouseMotionListener(this);
        game.getWindow().getCanvas().addMouseListener(this);
        game.getWindow().getCanvas().addMouseWheelListener(this);

    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            keysLast[i] = keys[i];
        }
        for (int i = 0; i < NUM_BUTTONS; i++) {
            buttonsLast[i] = buttons[i];
        }
    }

    public boolean isKey(int keyCode) {
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode) {
        return !keys[keyCode] && keysLast[keyCode];
    }

    public boolean isKeyDown(int keyCode) {
        return keys[keyCode] && !keysLast[keyCode];
    }

    public boolean isButton(int buttonCode) {
        return buttons[buttonCode];
    }

    public boolean isButtonUp(int buttonCode) {
        return !buttons[buttonCode] && buttonsLast[buttonCode];
    }

    public boolean isButtonDown(int buttonCode) {
        return buttons[buttonCode] && !buttonsLast[buttonCode];
    }

//
//    public class Key {
//        private int numTimesPressed = 0;
//        private boolean pressed = false;
//
//        public boolean isPressed() {
//            return pressed;
//        }
//
//        public void toggle(boolean isPressed) {
//            pressed = isPressed;
//            if (numTimesPressed >= 3) {
//                numTimesPressed = 0;
//            }
//            if (isPressed) {
//                numTimesPressed++;
//            }
//
//        }
//
//        public int getNumTimesPressed() {
//            return numTimesPressed;
//        }
//    }
//
//
//    public Key up = new Key();
//    public Key down = new Key();
//    public Key left = new Key();
//    public Key right = new Key();

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int) (e.getX() / game.getScale() + game.getWindow().getScreen().getBounds().x);
        mouseY = (int) (e.getY() / game.getScale() + game.getWindow().getScreen().getBounds().y);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scroll = e.getWheelRotation();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;

        //toggleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        //toggleKey(e.getKeyCode(), false);
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMouseX() {
        return mouseX;
    }

//    public void toggleKey(int keyKode, boolean isPressed) {
//        if (keyKode == KeyEvent.VK_W || keyKode == KeyEvent.VK_UP) {
//            up.toggle(isPressed);
//        }
//        if (keyKode == KeyEvent.VK_A || keyKode == KeyEvent.VK_LEFT) {
//            left.toggle(isPressed);
//        }
//        if (keyKode == KeyEvent.VK_S || keyKode == KeyEvent.VK_DOWN) {
//            down.toggle(isPressed);
//        }
//        if (keyKode == KeyEvent.VK_D || keyKode == KeyEvent.VK_RIGHT) {
//            right.toggle(isPressed);
//        }
//
//    }
}
