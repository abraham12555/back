<style>
    .loading-panel {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0px;
    overflow: hidden;
    }

    /* Icons */
    .icon-wrapper {
    position: absolute;
    width: 200px;
    height: 200px;
    border-radius: 100%;
    background: rgba(255, 255, 255, 0.3);
    top: 50%;
    margin-top: -100px;
    left: 50%;
    margin-left: -100px;
    }

    .first.loading-panel .icon-wrapper {
    animation: fade linear infinite, slide-in-bottom ease-out infinite;
    }

    .second.loading-panel .icon-wrapper {
    animation: fade linear infinite, slide-in-top ease-out infinite;
    }

    .icon-wrapper:before {
    content: ' ';
    width: 34%;
    height: 34%;
    background: rgba(255, 255, 255, 0.3);
    border-radius: 100%;
    position: absolute;
    }

    .first.loading-panel .icon-wrapper:before {
    top: -5%;
    left: -5%;
    animation: late-fade 10s linear infinite, circulate-clockwise 1s linear infinite;
    }

    .second.loading-panel .icon-wrapper:before {
    bottom: -5%;
    right: -5%;
    animation: late-fade 10s linear infinite, circulate-counterclockwise 1s linear infinite;
    }

    .icon-wrapper {
    position: relative;
    text-align: center;
    }

    .first.loading-panel .cloud {
    fill: #59c4fb;
    }

    .second.loading-panel .cloud {
    fill: #fff;
    }

    .icon-wrapper .icon {
    position: absolute;
    top: 50%;
    width: 100%;
    text-align: center;
    margin-top: -10px;
    }

    .first.loading-panel .icon {
    animation: spin-clockwise 2s ease-out infinite;
    }

    .second.loading-panel .icon {
    overflow: hidden;
    animation: blind-right 1.42857s ease-out infinite;
    left: 50%;
    margin-left: -17px;
    }

    /* Animations */
    @keyframes slide-in-left {
    0% {
    transform: translate(-100%);
    }
    10% {
    transform: translate(0);
    }
    80% {
    transform: translate(0);
    }
    90% {
    transform: translate(-100%);
    }
    100% {
    transform: translate(-100%);
    }
    }
    @keyframes slide-in-right {
    0% {
    transform: translate(100%);
    }
    10% {
    transform: translate(0);
    }
    80% {
    transform: translate(0);
    }
    90% {
    transform: translate(100%);
    }
    100% {
    transform: translate(100%);
    }
    }
    @keyframes slide-in-bottom {
    0% {
    top: 120%;
    }
    10% {
    top: 120%;
    }
    15% {
    top: 50%;
    }
    75% {
    top: 50%;
    }
    80% {
    top: 120%;
    }
    100% {
    top: 120%;
    }
    }
    @keyframes slide-in-top {
    0% {
    top: -20%;
    }
    10% {
    top: -20%;
    }
    15% {
    top: 50%;
    }
    75% {
    top: 50%;
    }
    80% {
    top: -20%;
    }
    100% {
    top: -20%;
    }
    }
    @keyframes fade {
    0% {
    opacity: 0;
    }
    10% {
    opacity: 0;
    }
    15% {
    opacity: 1;
    }
    75% {
    opacity: 1;
    }
    80% {
    opacity: 0;
    }
    100% {
    opacity: 0;
    }
    }
    @keyframes late-fade {
    0% {
    opacity: 0;
    }
    15% {
    opacity: 0;
    }
    20% {
    opacity: 1;
    }
    70% {
    opacity: 1;
    }
    75% {
    opacity: 0;
    }
    100% {
    opacity: 0;
    }
    }
    @keyframes circulate-clockwise {
    0% {
    transform: translate(0, 0);
    }
    25% {
    transform: translate(10%, 10%);
    }
    50% {
    transform: translate(0%, 20%);
    }
    75% {
    transform: translate(-10%, 10%);
    }
    }
    @keyframes circulate-counterclockwise {
    0% {
    transform: translate(0, 0);
    }
    25% {
    transform: translate(-10%, 10%);
    }
    50% {
    transform: translate(0%, 20%);
    }
    75% {
    transform: translate(10%, 10%);
    }
    }
    @keyframes spin-clockwise {
    0% {
    transform: rotate(0deg);
    }
    25% {
    transform: rotate(90deg);
    }
    50% {
    transform: rotate(180deg);
    }
    75% {
    transform: rotate(270deg);
    }
    100% {
    transform: rotate(360deg);
    }
    }
    @keyframes blind-right {
    0% {
    width: 0;
    opacity: 0;
    }
    1% {
    width: 0;
    opacity: 1;
    }
    50% {
    width: 35px;
    opacity: 1;
    }
    99% {
    width: 35px;
    opacity: 0;
    }
    100% {
    width: 0;
    opacity: 0;
    }
    }

</style>
<div class="idLightbox hide" id="modalSalvado">
    <div class="overlay"></div>
    <div class="loading-panel first">
        <div class="icon-wrapper">
            <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
            width="125px" height="100%" viewBox="0 0 33.271 22.313" enable-background="new 0 0 33.271 22.313" xml:space="preserve">
            <path class='cloud' d="M27.244,8.64c0.04-0.319,0.068-0.643,0.068-0.973C27.312,3.432,23.88,0,19.646,0
            c-3.069,0-6.047,2.208-7.047,5.208c-1.625-0.922-3.341-1.195-4.595-0.104c-0.012,0.01-0.019,0.022-0.031,0.033
            c-0.02,0.014-0.042,0.02-0.062,0.034c-1.236,0.875-1.686,2.49-1.336,4.123C6.543,9.292,6.51,9.292,6.51,9.292
            c-3.596,0-6.51,2.914-6.51,6.51c0,3.596,2.914,6.511,6.51,6.511h19.896c3.791,0,6.864-3.073,6.864-6.864
            C33.271,11.941,30.639,9.054,27.244,8.64z"/>
            </svg>
            <div class="icon">
                <svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                width="35px" viewBox="0 0 9.652 9.207" enable-background="new 0 0 9.652 9.207" xml:space="preserve">
                <path fill="#FFFFFF" d="M1.898,4.375c0.117-0.545,0.388-1.03,0.773-1.419l0.862,0.91c0.03,0.032,0.069,0.052,0.11,0.061
                c0.033,0.007,0.067,0.006,0.1-0.002c0.075-0.02,0.132-0.08,0.146-0.154l0.666-3.526C4.567,0.18,4.548,0.113,4.502,0.066
                C4.457,0.018,4.39-0.007,4.323,0.001L0.578,0.312C0.5,0.321,0.435,0.373,0.409,0.444c-0.027,0.071-0.01,0.151,0.044,0.208
                l0.958,0.981c-0.658,0.638-1.116,1.449-1.31,2.358C-0.217,5.479,0.224,7.043,1.28,8.178c0.135,0.145,0.307,0.237,0.488,0.276
                c0.279,0.06,0.582-0.008,0.808-0.208C2.949,7.917,2.519,7.641,2.32,7.269C1.87,6.422,1.7,5.302,1.898,4.375z"/>
                <path fill="#FFFFFF" d="M7.754,4.831C7.638,5.376,7.367,5.861,6.981,6.25L6.119,5.34C6.09,5.309,6.051,5.289,6.009,5.28
                c-0.033-0.007-0.067-0.007-0.1,0.002c-0.075,0.02-0.132,0.08-0.146,0.154L5.097,8.961C5.084,9.027,5.104,9.092,5.15,9.14
                c0.044,0.048,0.112,0.072,0.178,0.064l3.745-0.311c0.077-0.009,0.144-0.06,0.17-0.132c0.026-0.07,0.008-0.151-0.045-0.207
                L8.24,7.573C8.898,6.936,9.356,6.125,9.55,5.215c0.318-1.487-0.123-3.053-1.178-4.188C8.237,0.884,8.065,0.791,7.884,0.752
                C7.605,0.692,7.302,0.76,7.076,0.959c-0.373,0.33,0.059,0.605,0.257,0.979C7.783,2.784,7.953,3.904,7.754,4.831z"/>
                </svg>
            </div>
        </div>
    </div>
</div>