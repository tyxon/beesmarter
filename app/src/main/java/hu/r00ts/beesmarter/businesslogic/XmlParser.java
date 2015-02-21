package hu.r00ts.beesmarter.businesslogic;


import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;

import hu.r00ts.beesmarter.businesslogic.DTO.Key;
import hu.r00ts.beesmarter.businesslogic.DTO.KeyState;
import hu.r00ts.beesmarter.businesslogic.DTO.Pattern;
import hu.r00ts.beesmarter.businesslogic.DTO.Training;

public class XmlParser {

    private static final String ns = null;

    public static Training parseTraining(String in) {
        try {
            StringReader sr = new StringReader(in);
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(sr);
            parser.nextTag();
            return readTraining(parser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Training();
    }

    public static Pattern parsePattern(String in) {
        try {
            StringReader sr = new StringReader(in);
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(sr);
            parser.nextTag();
            return readPattern(parser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Pattern();
    }

    private static Training readTraining(XmlPullParser parser) throws IOException, XmlPullParserException {
        Training training = new Training();

        parser.require(XmlPullParser.START_TAG, ns, "training");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();
            if (name.equals("pattern")) {
                training.Patterns.add(readPattern(parser));
            } else {
                //skip(parser);
            }
        }

        return training;
    }

    private static Pattern readPattern(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, "pattern");

        Pattern pattern = new Pattern();
        Key key = null;
        Integer i = 0;

        while (parser.next() != XmlPullParser.END_TAG || !parser.getName().equals("pattern")) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            if (i % 2 == 0) {
                key = new Key();
                pattern.Keys.add(key);
            }

            String name = parser.getName();
            if (name.equals("key-down")) {
                key.KeyDown = readKey(parser);
            } else if (name.equals("key-up")) {
                key.KeyUp = readKey(parser);
            } else {
                //skip();
            }
            i++;
        }
        return pattern;
    }

    private static KeyState readKey(XmlPullParser parser) {
        KeyState keyState = new KeyState();
        keyState.Code = parser.getAttributeValue(ns, "code");
        keyState.Time = Integer.parseInt(parser.getAttributeValue(ns, "posix-time"));
        String x = parser.getAttributeValue(ns, "relative-pos-x");
        keyState.X = Integer.parseInt(x.substring(0, x.length()-1));
        String y = parser.getAttributeValue(ns, "relative-pos-y");
        keyState.Y = Integer.parseInt(y.substring(0, y.length() - 1));

        return keyState;
    }

}
