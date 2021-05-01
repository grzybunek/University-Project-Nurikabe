
public class Cell {

    private boolean fixed = false;  // false = komórka do zaznaczenia, true = komórka z cyfrą
    private int value = 0; // 0 = czerwona, 1 = biała, 2 = czarna, > 2 = tylko dla komórki z cyfrą
    private int row;
    private int column;

    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }

    public boolean isFixed() {
        return fixed;
    }


    public Cell(boolean fixed, int value) {

        this.value = value;
        this.fixed = fixed;

        }

        public void setValue (int wartosc) {

            if (this.fixed == true) {

                this.value = wartosc;

            } else if (this.fixed == false) {

                if (this.value > 2) {

                    this.value = 0;

                }

                switch (this.value) {

                    case (0):
                        this.value = 1;
                        break;

                    case (1):
                        this.value = 2;
                        break;

                    case (2):
                        this.value = 0;
                        break;

                }

            }

        }

        public int getValue () {
            return value;
        }

    }




