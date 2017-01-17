package com.dearjun.countschool.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dearjun.countschool.ExecuteCountSchool;
import com.dearjun.countschool.type.FindSchoolType;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

public class NLPAnalyzer {

    private int wordMorphIndex = 0;
    private String patternStr = null;
    protected static Pattern searchWordPattern = null;
    private Komoran komoran = null;

    public NLPAnalyzer(FindSchoolType schoolType) {
        this.patternStr = schoolType.getSchoolTypeStr();
        //searchWordPattern = Pattern.compile("^.{0,}(" + this.patternStr + "|" + patternStr.toCharArray()[0] + ")$", 2);
        searchWordPattern = Pattern.compile("^.{0,}(" + this.patternStr + ")$", 2);
        this.komoran = new Komoran(ExecuteCountSchool.class.getResource("/models").getPath());
    }

    @SuppressWarnings("unchecked")
    public String getWordExcludePatternStr(String targetWord, String pairTypeStr) {
        List<List<Pair<String, String>>> analyzeList = komoran.analyze(targetWord);
        String result = "";

        for(List<Pair<String, String>> analyze : analyzeList) {
            for(Pair<String, String> wordMorph : analyze) {
                String wordMorphType = wordMorph.getSecond();

                if(!this.isIncludePatternStr(wordMorph.getFirst())) {
                    try {
                        result = result + PrevWordMorphMakerType.valueOf(wordMorphType).getExtractWord(wordMorph);
                    } catch(IllegalArgumentException ie) {
                        continue;
                    }
                }
            }
        }

        return result;
    }

    private boolean isIncludePatternStr(String checkWord) {
        String[] patternArr = this.getPatternArr();
        boolean result = false;

        for(String patternStr : patternArr) {
            if(checkWord.equals(patternStr)) {
                result = true;

                break;
            }
        }

        return result;
    }

    private String[] getPatternArr() {
        return this.patternStr.split("\\|");
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

                    if(!"".equals(foundWord) && !equalsPatternString(patternStr, foundWord)) {
                        foundList.add(foundWord);
                    }

                    this.wordMorphIndex++;
                }
            }
        }

        return foundList;
    }

    private boolean equalsPatternString(String patternStr, String word) {
        boolean result = false;
        String[] patternStrArr = patternStr.split("\\|");

        for(String diffStr : patternStrArr) {
            if(result = word.equals(diffStr)) {
                break;
            }
        }

        return result;
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
        char[] calibrateWordArr = this.patternStr.toCharArray();

        while(tmpWordMorphIndex > 0) {
            Pair<String, String> prevWordMorph = wordMorphPairList.get(tmpWordMorphIndex - 1);

            if(wordMorphPairList.size() > 1) {
                try {
                    PrevWordMorphMakerType.valueOf(prevWordMorph.getSecond()).setPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
                } catch(IllegalArgumentException ie) {
                    continue;
                } finally {
                    tmpWordMorphIndex--;
                }
            }

        }
    }

    private enum PrevWordMorphMakerType {
        XSN {

            @Override
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr);
            }

            @Override
            protected String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph) {
                throw new UnsupportedOperationException();
            }

            @Override
            protected String getExtractWord(Pair<String, String> targetWordMorph) {
                return targetWordMorph.getFirst();
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

            @Override
            protected String getExtractWord(Pair<String, String> targetWordMorph) {
                return targetWordMorph.getFirst();
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

            @Override
            protected String getExtractWord(Pair<String, String> targetWordMorph) {
                return targetWordMorph.getFirst();
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

            @Override
            protected String getExtractWord(Pair<String, String> targetWordMorph) {
                return targetWordMorph.getFirst();
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

            @Override
            protected String getExtractWord(Pair<String, String> targetWordMorph) {
                return targetWordMorph.getFirst();
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

            @Override
            protected String getExtractWord(Pair<String, String> targetWordMorph) {
                return targetWordMorph.getFirst();
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

            @Override
            protected String getExtractWord(Pair<String, String> targetWordMorph) {
                return targetWordMorph.getFirst();
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

            @Override
            protected String getExtractWord(Pair<String, String> targetWordMorph) {
                return targetWordMorph.getFirst();
            }
        };

        protected abstract void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph,
                char[] calibrateWordArr);

        protected abstract String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph);

        protected abstract String getExtractWord(Pair<String, String> targetWordMorph);

        protected void executeSetPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph,
                char[] calibrateWordArr) {
            String prevWord = prevWordMorph.getFirst();
            Matcher matcher = searchWordPattern.matcher(prevWord);

            if(matcher.find()) {
                return;
            }

            if(detecteCalibWord(calibrateWordArr, prevWord, targetWordMorph.getFirst())) {
                targetWordMorph.setFirst(prevWord + targetWordMorph.getFirst());
            }
        }

        // 중복되는 단어의 보정 값을 확인한다.
        // 1. char 단위의 패턴에 속하는 단어인지 확인 (1글자 이전 단어의 중첩여부 보정을 위하여)
        // 2. 현재 단어를 기준으로 찾아진 이전 형태소에서 추출한 단어가 이미 현재 단어에 속해있는지를 확인 
        // 3. 1과 2를 논리 곱으로 하여 현재 단어의 앞에 붙여도 되는 중첩되지 않은 단어인지 결과를 리턴
        public static boolean detecteCalibWord(char[] calibrateWordArr, String targetWord, String containCheckWord) {
            boolean result = false;

            for(char calibWord : calibrateWordArr) {
                if(targetWord.equals(String.valueOf(calibWord))) {
                    result = true;

                    break;
                }
            }

            return result || !containCheckWord.contains(targetWord);
        }
    }
}
