package com.dearjun.countschool.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dearjun.countschool.ExecuteCountSchool;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

public class NLPAnalyzer {

    private int wordMorphIndex = 0;
    private char[] calibrateWordArr = null;
    private String patternStr = null;
    protected static Pattern searchWordPattern = null;
    private Komoran komoran = null;

    public void setCalibrateWordArr(char[] calibrateWordArr) {
        this.calibrateWordArr = calibrateWordArr;
    }

    public NLPAnalyzer(String patternStr) {
        this.patternStr = patternStr;
        searchWordPattern = Pattern.compile("^.{0,}(" + this.patternStr + ")$", 2);
        this.komoran = new Komoran(ExecuteCountSchool.class.getResource("/models").getPath());
        this.setCalibrateWordArr(patternStr.toCharArray());
    }

    @SuppressWarnings("unchecked")
    public String getWord(String targetWord) {
        List<List<Pair<String, String>>> analyzeList = komoran.analyze(targetWord);
        String result = "";

        for(List<Pair<String, String>> analyze : analyzeList) {
            //            this.wordMorphIndex = 0;

            for(Pair<String, String> wordMorph : analyze) {
                if("NNG".equals(wordMorph.getSecond())) {
                    result = result + wordMorph.getFirst();
                }
                //                this.wordMorphIndex++;
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<String> getAnalyedWordList(List<String> targetListToAnalyze) {
        List<String> foundList = new ArrayList<String>();

        for(String tmpList : targetListToAnalyze) {
            List<List<Pair<String, String>>> resultList = komoran.analyze(tmpList);

            for(List<Pair<String, String>> result : resultList) {
                this.wordMorphIndex = 0;

                for(Pair<String, String> wordMorph : result) {
                    String foundWord = this.getFoundWord(wordMorph, result);

                    if(!"".equals(foundWord) && !(this.patternStr).equals(foundWord)) {
                        foundList.add(foundWord);
                    }
                    this.wordMorphIndex++;
                }
            }
        }

        return foundList;
    }

    private String getFoundWord(Pair<String, String> wordMorph, List<Pair<String, String>> wordMorphPairList) {
        String foundWord = "";

        if("NNG".equals(wordMorph.getSecond())) { // 형태소가 명사 인 것만 추출
            Matcher matcher = searchWordPattern.matcher(wordMorph.getFirst());

            while(matcher.find()) {
                this.setAndFindPrevWord(wordMorph, wordMorphPairList);
                foundWord = wordMorph.getFirst();
            }
        }

        return foundWord;
    }

    private void setAndFindPrevWord(Pair<String, String> targetWordMorph, List<Pair<String, String>> wordMorphPairList) {
        int tmpWordMorphIndex = this.wordMorphIndex;

        while(tmpWordMorphIndex > 0) {
            Pair<String, String> prevWordMorph = wordMorphPairList.get(tmpWordMorphIndex - 1);

            if(wordMorphPairList.size() > 1) {
                try {
                    PrevWordMorphType.valueOf(prevWordMorph.getSecond()).setPrevWord(targetWordMorph, prevWordMorph, this.calibrateWordArr);
                } catch(IllegalArgumentException ie) {
                    continue;
                } finally {
                    tmpWordMorphIndex--;
                }
            }

        }
    }

    private enum PrevWordMorphType {
        XSN {

            @Override
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
            }

            @Override
            protected String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph) {
                throw new UnsupportedOperationException();
            }
        },
        XPN {

            @Override
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
            }

            @Override
            protected String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph) {
                throw new UnsupportedOperationException();
            }
        },
        VV {

            @Override
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
            }

            @Override
            protected String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph) {
                throw new UnsupportedOperationException();
            }
        },
        MM {

            @Override
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
            }

            @Override
            protected String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph) {
                throw new UnsupportedOperationException();
            }
        },
        NNB {

            @Override
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
            }

            @Override
            protected String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph) {
                throw new UnsupportedOperationException();
            }
        },
        NNG {

            @Override
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
            }

            @Override
            protected String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph) {
                throw new UnsupportedOperationException();
            }
        },
        NNP {

            @Override
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
            }

            @Override
            protected String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph) {
                String locationWord = null;

                if(this.toString().equals(targetWordMorph.getSecond())) {
                    locationWord = targetWordMorph.getFirst();
                }

                return locationWord;
            }
        },
        JX {

            @Override
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
            }

            @Override
            protected String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph) {
                throw new UnsupportedOperationException();
            }
        };

        protected abstract void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph,
                char[] calibrateWordArr);

        protected abstract String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph);

        protected void executeSetPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph,
                char[] calibrateWordArr) {
            String tmpWord = prevWordMorph.getFirst();
            Matcher matcher = searchWordPattern.matcher(tmpWord);

            if(matcher.find()) {
                return;
            }

            boolean isDetectedCalibWord = this.detecteCalibWord(calibrateWordArr, tmpWord);

            if(isDetectedCalibWord || !targetWordMorph.getFirst().contains(tmpWord)) {
                targetWordMorph.setFirst(tmpWord + targetWordMorph.getFirst());
            }
        }

        private boolean detecteCalibWord(char[] calibrateWordArr, String targetWord) {
            boolean result = false;

            for(char calibWord : calibrateWordArr) {
                if(targetWord.equals(String.valueOf(calibWord))) {
                    result = true;

                    break;
                }
            }

            return result;
        }
    }
}
