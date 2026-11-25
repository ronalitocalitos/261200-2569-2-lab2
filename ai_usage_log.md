# AI Usage Log (`AI_LOG.md`)

* **Model Used:** Gemini 3.1 Pro
* **Date:** November 25, 2026
* **Assignment:** Lab 02 - Building Classes (`AirPurifier`, `Mode`, `Lab02`)
* **Usage Policy Note:** AI was used primarily for conceptual explanations, code review, debugging guidance, and generating sample test data. Core logic and implementations were written and modified by the student.

---

## Prompt History & Summary of Interactions

### 1. Conceptual Understanding: `enum` vs `struct` vs `class`
* **User Prompts:**
  * *"enum คือไร"*
  * *"ฟิลเหมือน struc งี้หรอ"*
  * *"ใช้classแทนได้ไหม"*
* **AI Assistance:** Explained Java `enum` as a fixed set of constants (distinct from C++ `struct`), compared it with using `class` constants or raw `String`, and recommended using `enum Mode` for type safety.

### 2. Code Review & Debugging (`src/AirPurifier.java`)
* **User Prompts:**
  * Submitted initial `src/AirPurifier.java` code and asked *"ถูกต้องไหม"*
  * Submitted updated `src/AirPurifier.java` and asked *"เสร็จละถูกไหมแล้วข้อ4ทำไรต่อ"*
* **AI Assistance:**
  * Identified a syntax error (trailing comma in constructor parameters).
  * Identified a logic bug: missing `this.isOn = isOn;` in the constructor.
  * Identified a logic bug in `setFanSpeed()`: `if (!isOn && this.mode != Mode.MANUAL)` should use `||` instead of `&&`.
  * Provided a checklist of test cases to write in `src/Lab02.java`.

### 3. Sample Test Data Generation (`src/Lab02.java`)
* **User Prompts:**
  * *"กำหนดค่าในnew AirPurifier(...)มาหน่อยขกเขียนขอ3รุ่น"*
  * *"สร้างเพิ่มให้หน่อย"* (with existing `src/Lab02.java` code)
* **AI Assistance:** Generated sample constructor arguments for 3–5 `AirPurifier` objects (`Xiaomi 4 Pro`, `Dyson Pure Cool`, `Sharp Plasmacluster`) with varied initial states to test modes and `mostPopularModel()`.

### 4. Logic Refinement for `AUTO` Mode & PM2.5 Updates
* **User Prompts:**
  * Asked whether changing PM2.5 and calling `setMode(Mode.AUTO)` again would work or lose points.
  * Submitted updated `src/AirPurifier.java` with `changePm25(int pm)` and asked about keeping the `AUTO` condition in `setMode()`.
* **AI Assistance:**
  * Pointed out that `if (!isOn || mode == this.mode)` blocked re-running `AUTO` logic.
  * Suggested adding a dedicated `changePm25(int pm)` method.
  * Identified a bug in the `else` branch of `setMode()` where `if (this.mode == Mode.AUTO)` prevented `fanSpeed` from updating when switching from another mode to `AUTO`.

### 5. Class Variables & Methods (`static` & `HashMap`)
* **User Prompts:**
  * *"totalNumberEach ประกาศยังไงในclass"*
  * *"ในconstructorเราต้องเขียนยังไงตอนเพิ่มค่าในtotalNumberEachตอนสร้างเครื่องกรอกแต่ละอัน"*
  * *"ขอแนวทางให้ผมทำเองหน่อย"* (regarding `HashMap` methods for `mostPopularModel()` and `averageCountPerModel()`).
  * Asked about tie-breaking behavior in `mostPopularModel()` and omitting `averageCountPerModel()`.
* **AI Assistance:**
  * Showed how to import and initialize `static HashMap<String, Integer> totalNumberEach = new HashMap<>();`.
  * Explained how to increment counts in the constructor using `containsKey()`, `get()`, and `put()`.
  * Explained `HashMap` methods (`isEmpty()`, `size()`, `keySet()`, `get()`) so the student could implement `mostPopularModel()` independently.

### 6. Final Verification
* **User Prompts:**
  * Submitted final `src/AirPurifier.java` and `Lab02.java` for review before Gradescope submission.
* **AI Assistance:** Verified that documentation comments matched the final code (after removing `averageCountPerModel()`) and reminded to include `src/Mode.java` in the submission.

---

## Summary of Changes Made to the Codebase

* **`src/Mode.java`**
  * Created `enum Mode` with values `SLEEP`, `POWER`, `MANUAL`, and `AUTO`.
* **`src/AirPurifier.java`**
  * Fixed constructor syntax (removed trailing comma) and added `this.isOn = isOn;`.
  * Fixed boolean condition in `setFanSpeed()` from `&&` to `||`.
  * Fixed `AUTO` mode branch in `setMode()` so `fanSpeed` updates immediately when switching into `AUTO`.
  * Added `changePm25(int pm)` to update PM2.5 density and recalculate `fanSpeed` when in `AUTO` mode.
  * Added class variables `static int totalNumber` and `static HashMap<String, Integer> totalNumberEach`, updated inside the constructor.
  * Implemented `public static String mostPopularModel()` to find the model with the highest count.
  * Updated header comments (Implementation Plan) to match the final methods.
* **`src/Lab02.java`**
  * Added 4 `AirPurifier` instances (`p1`–`p4`, including duplicate `"Xiaomi 4 Pro"` models).
  * Added test sequences covering power on/off, all 4 modes, fan speed wrapping (`1 -> 2 -> 3 -> 4 -> 1`), `changePm25()`, `AirPurifier.totalNumber`, and `AirPurifier.mostPopularModel()`.