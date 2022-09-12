import synthesizer.*;

public class GuitarHero {


    public static void main(String[] args) {

        GuitarString stringQ = new GuitarString(CONCERT.CONCERT_Q);
        GuitarString string2 = new GuitarString(CONCERT.CONCERT_2);
        GuitarString stringW = new GuitarString(CONCERT.CONCERT_W);
        GuitarString stringE = new GuitarString(CONCERT.CONCERT_E);
        GuitarString string4 = new GuitarString(CONCERT.CONCERT_4);
        GuitarString stringR = new GuitarString(CONCERT.CONCERT_R);
        GuitarString string5 = new GuitarString(CONCERT.CONCERT_5);
        GuitarString stringT = new GuitarString(CONCERT.CONCERT_T);
        GuitarString stringY = new GuitarString(CONCERT.CONCERT_Y);
        GuitarString string7 = new GuitarString(CONCERT.CONCERT_7);
        GuitarString stringU = new GuitarString(CONCERT.CONCERT_U);
        GuitarString string8 = new GuitarString(CONCERT.CONCERT_8);
        GuitarString stringI = new GuitarString(CONCERT.CONCERT_I);
        GuitarString string9 = new GuitarString(CONCERT.CONCERT_9);
        GuitarString stringO = new GuitarString(CONCERT.CONCERT_O);
        GuitarString stringP = new GuitarString(CONCERT.CONCERT_P);
        GuitarString stringMINUS = new GuitarString(CONCERT.CONCERT_MINUS);
        GuitarString stringSQUARE = new GuitarString(CONCERT.CONCERT_SQUARE);
        GuitarString stringEQUAL = new GuitarString(CONCERT.CONCERT_EQUAL);
        GuitarString stringZ = new GuitarString(CONCERT.CONCERT_Z);
        GuitarString stringX = new GuitarString(CONCERT.CONCERT_X);
        GuitarString stringD = new GuitarString(CONCERT.CONCERT_D);
        GuitarString stringC = new GuitarString(CONCERT.CONCERT_C);
        GuitarString stringF = new GuitarString(CONCERT.CONCERT_F);
        GuitarString stringV = new GuitarString(CONCERT.CONCERT_V);
        GuitarString stringG = new GuitarString(CONCERT.CONCERT_G);
        GuitarString stringB = new GuitarString(CONCERT.CONCERT_B);
        GuitarString stringN = new GuitarString(CONCERT.CONCERT_N);
        GuitarString stringJ = new GuitarString(CONCERT.CONCERT_J);
        GuitarString stringM = new GuitarString(CONCERT.CONCERT_M);
        GuitarString stringK = new GuitarString(CONCERT.CONCERT_K);
        GuitarString stringCOMMA = new GuitarString(CONCERT.CONCERT_COMMA);
        GuitarString stringPERIOD = new GuitarString(CONCERT.CONCERT_PERIOD);
        GuitarString stringSEMICOLON = new GuitarString(CONCERT.CONCERT_SEMICOLON);
        GuitarString stringSLASH = new GuitarString(CONCERT.CONCERT_SLASH);
        GuitarString stringQUOTES = new GuitarString(CONCERT.CONCERT_QUOTES);
        GuitarString stringDQUOTES = new GuitarString(CONCERT.CONCERT_DQUOTES);


        while (true) {

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();

                switch (key) {
                    case 'q':
                        stringQ.pluck();
                        break;
                    case '2':
                        string2.pluck();
                        break;
                    case 'w':
                        stringW.pluck();
                        break;
                    case 'e':
                        stringE.pluck();
                        break;
                    case '4':
                        string4.pluck();
                        break;
                    case 'r':
                        stringR.pluck();
                        break;
                    case '5':
                        string5.pluck();
                        break;
                    case 't':
                        stringT.pluck();
                        break;
                    case 'y':
                        stringY.pluck();
                        break;
                    case '7':
                        string7.pluck();
                        break;
                    case 'u':
                        stringU.pluck();
                        break;
                    case '8':
                        string8.pluck();
                        break;
                    case 'i':
                        stringI.pluck();
                        break;
                    case '9':
                        string9.pluck();
                        break;
                    case 'o':
                        stringO.pluck();
                        break;
                    case 'p':
                        stringP.pluck();
                        break;
                    case '-':
                        stringMINUS.pluck();
                        break;
                    case '[':
                        stringSQUARE.pluck();
                        break;
                    case '=':
                        stringEQUAL.pluck();
                        break;
                    case 'z':
                        stringZ.pluck();
                        break;
                    case 'x':
                        stringX.pluck();
                        break;
                    case 'D':
                        stringD.pluck();
                        break;
                    case 'c':
                        stringC.pluck();
                        break;
                    case 'f':
                        stringF.pluck();
                        break;
                    case 'v':
                        stringV.pluck();
                        break;
                    case 'g':
                        stringG.pluck();
                        break;
                    case 'b':
                        stringB.pluck();
                        break;
                    case 'n':
                        stringN.pluck();
                        break;
                    case 'j':
                        stringJ.pluck();
                        break;
                    case 'm':
                        stringM.pluck();
                        break;
                    case 'k':
                        stringK.pluck();
                        break;
                    case ',':
                        stringCOMMA.pluck();
                        break;
                    case '.':
                        stringPERIOD.pluck();
                        break;
                    case ';':
                        stringSEMICOLON.pluck();
                        break;
                    case '/':
                        stringSLASH.pluck();
                        break;
                    case '\'':
                        stringQUOTES.pluck();
                        break;
                    case '\"':
                        stringDQUOTES.pluck();
                        break;
                    default:
                }
            }

            double sample = stringQ.sample() + string2.sample() + stringW.sample() + stringE.sample() + string4.sample()
                    + stringR.sample() + string5.sample() + stringT.sample() + stringY.sample() + string7.sample() + stringU.sample()
                    + string8.sample() + stringI.sample() + string9.sample() + stringO.sample() + stringP.sample() + stringMINUS.sample()
                    + stringSQUARE.sample() + stringEQUAL.sample() + stringZ.sample() + stringX.sample() + stringD.sample() + stringC.sample()
                    + stringF.sample() + stringV.sample() + stringG.sample() + stringB.sample() + stringN.sample() + stringJ.sample()
                    + stringM.sample() + stringK.sample() + stringCOMMA.sample() + stringPERIOD.sample() + stringSEMICOLON.sample()
                    + stringSLASH.sample() + stringQUOTES.sample() + stringDQUOTES.sample();

            StdAudio.play(sample);

            stringQ.tic();
            string2.tic();
            stringW.tic();
            stringE.tic();
            string4.tic();
            stringR.tic();
            string5.tic();
            stringT.tic();
            stringY.tic();
            string7.tic();
            stringU.tic();
            string8.tic();
            stringI.tic();
            string9.tic();
            stringO.tic();
            stringP.tic();
            stringMINUS.tic();
            stringSQUARE.tic();
            stringEQUAL.tic();
            stringZ.tic();
            stringX.tic();
            stringD.tic();
            stringC.tic();
            stringF.tic();
            stringV.tic();
            stringG.tic();
            stringB.tic();
            stringN.tic();
            stringJ.tic();
            stringM.tic();
            stringK.tic();
            stringCOMMA.tic();
            stringPERIOD.tic();
            stringSEMICOLON.tic();
            stringSLASH.tic();
            stringQUOTES.tic();
            stringDQUOTES.tic();


        }
    }
}
