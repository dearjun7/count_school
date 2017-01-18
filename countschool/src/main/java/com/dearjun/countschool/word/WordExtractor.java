package com.dearjun.countschool.word;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dearjun.countschool.ExecuteCountSchool;
import com.dearjun.countschool.type.FindWordType;
import com.dearjun.countschool.utils.StringUtil;

import kr.co.shineware.nlp.komoran.core.analyzer.Komoran;
import kr.co.shineware.util.common.model.Pair;

public class WordExtractor {

    private int wordMorphIndex = 0;
    private Komoran komoran = null;
    private FindWordType findWordType = null;

    public WordExtractor(FindWordType findWordType) {
        this.findWordType = findWordType;
        this.komoran = new Komoran(ExecuteCountSchool.class.getResource("/models").getPath());
    }

    public FindWordType getFindWordType() {
        return this.findWordType;
    }

    public void destroy() {
        this.wordMorphIndex = 0;
        this.komoran = null;
        this.findWordType = null;
    }

    @SuppressWarnings("unchecked")
    public String getWordExcludePatternStr(String targetWord, String pairTypeStr, boolean addPatternWordToEnd, boolean isCalibratedWord) {
        List<List<Pair<String, String>>> analyzeList = this.komoran.analyze(targetWord);
        String result = "";

        for(List<Pair<String, String>> analyze : analyzeList) {
            for(Pair<String, String> wordMorph : analyze) {
                String wordMorphType = wordMorph.getSecond();

                if(!StringUtil.equalsPatternString(wordMorph.getFirst(), this.findWordType.getFindWordStr())) {
                    try {
                        result = result + PrevWordMorphMakerType.valueOf(wordMorphType).getExtractWord(wordMorph);
                    } catch(IllegalArgumentException ie) {
                        continue;
                    }
                }
            }
        }

        //        char endChar = this.findWordType.getFindWordStr().toCharArray()[0];

        if(!(result.length() == 1 && !isCalibratedWord)) {
            String endStr = this.findWordType.getFindWordStr();

            if(result.length() > 1 && result.endsWith(String.valueOf(this.findWordType.getFindWordStr().toCharArray()[0]))) {
                result = result.substring(0, result.length() - 1);
            }

            if(addPatternWordToEnd && !result.endsWith(endStr)) {
                result = result + endStr;
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<String> getAnalyedWordList(List<String> targetListToAnalyze) {
        List<String> foundList = new ArrayList<String>();

        for(String tmpList : targetListToAnalyze) {
            List<List<Pair<String, String>>> resultList = this.komoran.analyze(tmpList);

            for(List<Pair<String, String>> result : resultList) {
                this.wordMorphIndex = 0;

                for(Pair<String, String> wordMorph : result) {
                    String foundWord = this.getFoundWord(wordMorph, result);

                    if(!"".equals(foundWord) && !this.isTooShortFoundWord(foundWord)
                            && !StringUtil.equalsPatternString(this.findWordType.getFindWordStr(), foundWord)) {
                        foundList.add(foundWord);
                    }

                    this.wordMorphIndex++;
                }
            }
        }

        return foundList;
    }

    private boolean isTooShortFoundWord(String foundWord) {
        boolean result = false;
        String findWordStr = this.findWordType.getFindWordStr();
        String shortFindWord = String.valueOf(findWordStr.toCharArray()[0]);

        if(foundWord.length() == 2 && foundWord.endsWith(shortFindWord)) {
            result = true;
        }

        return result;
    }

    private String getFoundWord(Pair<String, String> wordMorph, List<Pair<String, String>> wordMorphPairList) {
        String foundWord = "";

        if("NNG".equals(wordMorph.getSecond()) || "NNP".equals(wordMorph.getSecond())) { // 형태소가 명사 인 것만 추출
            Matcher matcher = this.findWordType.getFindPattern().matcher(wordMorph.getFirst());

            while(matcher.find()) {
                if(!this.isDetectedCalibWordFromNextWord(wordMorphPairList)) {
                    this.setAndFindPrevWord(wordMorph, wordMorphPairList);
                    foundWord = wordMorph.getFirst();
                }
            }
        }

        return foundWord;
    }

    private boolean isDetectedCalibWordFromNextWord(List<Pair<String, String>> wordMorphPairList) {
        boolean result = false;
        String[] wordForFoundedWordCalibArr = this.findWordType.getWordForFoundedWordCalib();

        for(Pair<String, String> wordMorph : wordMorphPairList) {
            boolean isBreakLoop = false;

            if(wordForFoundedWordCalibArr == null) {
                isBreakLoop = true;
                break;
            }

            for(String wordForFoundedWordCalib : wordForFoundedWordCalibArr) {
                if(wordMorph.getFirst().contains(wordForFoundedWordCalib)) {
                    result = true;
                    isBreakLoop = true;
                    break;
                }
            }

            if(isBreakLoop) {
                break;
            }
        }

        return result;
    }

    private void setAndFindPrevWord(Pair<String, String> targetWordMorph, List<Pair<String, String>> wordMorphPairList) {
        int tmpWordMorphIndex = this.wordMorphIndex;
        char[] calibrateWordArr = this.findWordType.getFindWordStr().toCharArray();

        while(tmpWordMorphIndex > 0) {
            Pair<String, String> prevWordMorph = wordMorphPairList.get(tmpWordMorphIndex - 1);

            if(wordMorphPairList.size() > 1) {
                try {
                    PrevWordMorphMakerType.valueOf(prevWordMorph.getSecond()).setPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr,
                            this.findWordType.getFindPattern());
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
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr,
                    Pattern searchWordPattern) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr, searchWordPattern);
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
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr,
                    Pattern searchWordPattern) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr, searchWordPattern);
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
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr,
                    Pattern searchWordPattern) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr, searchWordPattern);
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
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr,
                    Pattern searchWordPattern) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr, searchWordPattern);
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
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr,
                    Pattern searchWordPattern) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr, searchWordPattern);
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
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr,
                    Pattern searchWordPattern) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr, searchWordPattern);
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
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr,
                    Pattern searchWordPattern) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr, searchWordPattern);
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
            protected void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr,
                    Pattern searchWordPattern) {
                this.executeSetPrevWord(targetWordMorph, prevWordMorph, calibrateWordArr, searchWordPattern);
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

        /**
         * searchWordPattern을 통해 찾아진 단어의 조합을 보정하기 위하여, 이전 단어(prevWordMorph)에서 단어
         * 조합을 추출하여 targetWordMorph에 first 항목에 set한다.
         * 
         * @param targetWordMorph
         *            - 저장할 대상 단어 조합 항목
         * @param prevWordMorph
         *            - 이전 단어 조합 항목
         * @param calibrateWordArr
         *            - 중복 체크 searchWordPattern의 char 단위 배열
         * @param searchWordPattern
         *            - 검색할 중심단어 패턴
         */
        protected abstract void setPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph,
                char[] calibrateWordArr, Pattern searchWordPattern);

        /**
         * NNP 타입의 형태소에서 지역 단어를 리턴한다.
         * 
         * @param targetWordMorph
         * @return 형태소에서 추출된 지역명 String
         */
        protected abstract String getCheckedLocationPrevWord(Pair<String, String> targetWordMorph);

        /**
         * 추출된 NNP-지역, NNG-단어, (JX, MM, NNB, VV, XPN, XSN - 보정을 위한 타입) 타입 형태소의
         * 단어 String을 리턴한다.
         * 
         * @param targetWordMorph
         * @return 형태소에서 추출된 단어 String
         */
        protected abstract String getExtractWord(Pair<String, String> targetWordMorph);

        protected void executeSetPrevWord(Pair<String, String> targetWordMorph, Pair<String, String> prevWordMorph, char[] calibrateWordArr,
                Pattern searchWordPattern) {
            String prevWord = prevWordMorph.getFirst();
            Matcher matcher = searchWordPattern.matcher(prevWord);

            if(matcher.find() && prevWord.length() != 1) {
                return;
            }

            if(StringUtil.checkDuplicatedWord(calibrateWordArr, prevWord, targetWordMorph.getFirst())) {
                targetWordMorph.setFirst(prevWord + targetWordMorph.getFirst());
            }
        }
    }
}
